package com.example.grupo8_elpaseotelolleva;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import Modelo.Node;
import Modelo.Product;
import Modelo.User;
import io.ProductoApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CarritoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    CheckBox tarjeta;
    CheckBox efectivo;
    private List<Node> nodes= new ArrayList<>();
    private ProductoApiService serv;
    private Spinner spinner;
    private String[] arregloNodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrito);
        actualizarCarrito();
        obtenerNodos();
    }

    public void verCheckBox(View view){
        tarjeta = (CheckBox) findViewById(R.id.seleccionTarjeta);
        efectivo = (CheckBox) findViewById(R.id.seleccionEfectivo);
        final Intent i;
        if (tarjeta.isChecked() && efectivo.isChecked()){
            Toast toast = Toast.makeText(CarritoActivity.this, "Ingrese un único método de pago", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            if (tarjeta.isChecked()) {
                i = new Intent(this, ConfiguracionPago.class);
                startActivity(i);
            } else {
                if (efectivo.isChecked()) {
                    Toast toast = Toast.makeText(CarritoActivity.this, "Compra finalizada, en breve te llegará un email. ", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(CarritoActivity.this, "Por favor seleccione un método de pago. ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
    }

    public void actualizarCarrito(){
        ListView listView = (ListView) findViewById(R.id.carritoView);
        listView.setAdapter(new CustomAdapterCarrito(CarritoActivity.this));

        TextView vistaTotal = (TextView) findViewById(R.id.totalCarrito);

        float total = 0;
        for (Product p: MainActivity.instanceCarrito.getProductos()){
            total = total + (p.getPrice() * p.getCantidadEnElCarrito());

        }
        vistaTotal.setText("El total es de $" + ((total)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent i ;
        switch(item.getItemId()){
            case R.id.carrito :
                i = new Intent(this,CarritoActivity.class);
                startActivity(i);
                break;
            case R.id.productos :
                i = new Intent(this,ProductosActivity.class);
                startActivity(i);
                break;
            default :

        }
        return false;
    }
    private void obtenerNodos(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        serv = retrofit.create(ProductoApiService.class);

        serv.getNodes().enqueue(new Callback<List<Node>>() {
            @Override
            public void onResponse(Call<List<Node>> call, Response<List<Node>> response) {
                nodes = response.body();
                generarSpinner();
            }

            @Override
            public void onFailure(Call<List<Node>> call, Throwable t) {
                String error = t.getMessage();
            }
        });
    }
    private void generarSpinner(){
        arregloNodos = new String[nodes.size() + 1];
        arregloNodos[0] = "Entrega a domicilio";
        for(int j=0; j<nodes.size();j=j+1){
            arregloNodos[j+1] = nodes.get(j).getName();
        }
        spinner = findViewById(R.id.nodos);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arregloNodos));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
