package com.example.grupo8_elpaseotelolleva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;



import Modelo.Product;
import io.ProductoApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class DetalleProductoActivity extends AppCompatActivity {
    private ProductoApiService serv;
    private Product producto = new Product();
    private ArrayList<Product> productosDelCarrito = MainActivity.instanceCarrito.getProductos();
    private ProgressDialog dialog;
    private int contadorImagen=0;
    private int maxImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_producto);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Buscando el producto...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        Bundle datos = getIntent().getExtras();
        if (datos != null)
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.URL)
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .build();

            serv = retrofit.create(ProductoApiService.class);

            serv.getProductoPorId(datos.getLong("idProdcuto")).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    producto = response.body();
                    maxImagenes = producto.getImages().size();
                    if (maxImagenes == 1){
                        ocultarCambioImagenes();
                    }
                    TextView textView = findViewById(R.id.nombreProductoDetalle);
                    textView.setText(producto.getTitle());
                    textView = findViewById(R.id.precioProductoDetalle);
                    textView.setText("Precio: $" + String.valueOf(producto.getPrice()));
                    textView = findViewById(R.id.descripcionProductoDetalle);
                    textView.setText("Descripcion: " +producto.getDescription());
                    textView = findViewById(R.id.productorProductoDetalle);
                    textView.setText("Productor: " +producto.getProducer().getName());

                    ImageView imageView = findViewById(R.id.imagenProductoDetalle);

                    Bitmap bitmap = obtenerBitmap(contadorImagen);

                    imageView.setImageBitmap(bitmap);

                    dialog.hide();
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
        }

    }

    public void agregarAlCarrito(View v){
        boolean b = false;
        for (Product pr :productosDelCarrito){
            if (pr.getId() == producto.getId()){
                b=true;
                pr.setCantidadEnElCarrito(pr.getCantidadEnElCarrito() + 1);
            }
        }
        if (! b){
            productosDelCarrito.add(producto);
        }
        Toast toast = Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void volver(View v){
        Intent i = new Intent(this,ProductosActivity.class);
        startActivity(i);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu3,menu);
        return true;
    }

    private Bitmap obtenerBitmap(int pos){
        String decodedStringPure = String.valueOf(producto.getImages().get(pos).getValue());
        byte[] decodedString = Base64.decode(decodedStringPure.substring(22,decodedStringPure.length()), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        int maxHeight = 1000;
        int maxWidth = 1000;
        float scale = Math.min(((float)maxHeight / decodedByte.getWidth()), ((float)maxWidth / decodedByte.getHeight()));

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);
        return  decodedByte;
    }
    public void cambiarDerecha(View v){
        if (contadorImagen < maxImagenes-1){
            contadorImagen++;
        }
        else{
            contadorImagen = 0;
        }
        Bitmap bm = obtenerBitmap(contadorImagen);
        ImageView imageView = findViewById(R.id.imagenProductoDetalle);
        imageView.setImageBitmap(bm);
    }
    public void cambiarIzquierda(View v){
        if (contadorImagen == 0){
            contadorImagen = maxImagenes-1;
        }
        else{
            contadorImagen--;
        }
        Bitmap bm = obtenerBitmap(contadorImagen);
        ImageView imageView = findViewById(R.id.imagenProductoDetalle);
        imageView.setImageBitmap(bm);
    }
    private void ocultarCambioImagenes(){
        TextView button = findViewById(R.id.anterior);
        button.setVisibility(View.GONE);
        button = findViewById(R.id.siguiente);
        button.setVisibility(View.GONE);
    }
}
