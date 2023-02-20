package com.example.grupo8_elpaseotelolleva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.util.List;


import Modelo.Category;
import Modelo.Product;
import Modelo.User;
import io.ProductoApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ProductosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ProductoApiService serv;
    private List<Product> producs = new ArrayList<>();
    private List<Category> categorias = new ArrayList<>();
    private Spinner spinner;
    private String[] arregloCategoria;
    private ProgressDialog dialog;
    private User user = MainActivity.instanceUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Buscando los productos...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        buscarCategorias();
        listarProductos();

    }


    private void listarProductos(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        serv = retrofit.create(ProductoApiService.class);

        serv.getProductos().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                producs = response.body();
                ListView listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(new MyCustomAdapter(producs, ProductosActivity.this));
                dialog.hide();

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void buscarPorTitulo(View view) {
        List<Product> listita = new ArrayList<>();
        EditText editText = findViewById(R.id.barraDeBusqueda);
        String nombreProducto = editText.getText().toString();
        for(int j=0; j<producs.size();j=j+1){
            if((producs.get(j).getTitle().toLowerCase().contains(nombreProducto.toLowerCase()))){
                    listita.add(producs.get(j));
            }

        }
        if (! listita.isEmpty()){
            ListView listView = findViewById(R.id.listview);
            listView.setAdapter(new MyCustomAdapter(listita, ProductosActivity.this));
        }
        else{
            Toast toast = Toast.makeText(ProductosActivity.this, "No existe un producto con ese nombre ", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    private void buscarCategorias() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        serv = retrofit.create(ProductoApiService.class);

        serv.getCategorias().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categorias = response.body();
                arregloCategoria = new String[categorias.size() + 1];
                arregloCategoria[0] = "Todos";
                for(int j=0; j<categorias.size();j=j+1){
                    arregloCategoria[j+1] = categorias.get(j).getName();
                }
                generarSpinner();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent i ;
        switch(item.getItemId()){
            case R.id.carrito :
                if (user.getEmail() != null){
                    i = new Intent(this,CarritoActivity.class);
                    startActivity(i);}
            else {
                Toast toast = Toast.makeText(ProductosActivity.this, "Debe iniciar sesion si quiere ver su carrito", Toast.LENGTH_SHORT);
                toast.show();
            }
                break;
            case R.id.productos :
                i = new Intent(this,ProductosActivity.class);
                startActivity(i);
                break;
            default :

        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (! producs.isEmpty()) {
            String categoria = (String) parent.getItemAtPosition(position);

            List<Product> listita = new ArrayList<>();
            if (categoria.compareTo("Todos") == 0) {
                listita = producs;
            }
            else {
                for (int j = 0; j < producs.size(); j = j + 1) {
                    for (int z = 0; z < producs.get(j).getCategory().size(); z = z + 1) {
                        if ((producs.get(j).getCategory().get(z).getName().toLowerCase().compareTo(categoria.toLowerCase()) == 0)) {
                            listita.add(producs.get(j));
                        }
                    }
                }
            }
                if (! listita.isEmpty()) {
                    ListView listView = (ListView) findViewById(R.id.listview);
                    listView.setAdapter(new MyCustomAdapter(listita, ProductosActivity.this));
                } else {
                    Toast toast = Toast.makeText(ProductosActivity.this, "No hay productos con esta categoria ", Toast.LENGTH_LONG);
                    toast.show();
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void generarSpinner(){
        spinner = findViewById(R.id.categoriasASeleccionar);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arregloCategoria));

    }

}
