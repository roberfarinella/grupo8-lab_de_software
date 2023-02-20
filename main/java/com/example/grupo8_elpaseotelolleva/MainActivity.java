package com.example.grupo8_elpaseotelolleva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import Modelo.Cart;
import Modelo.Product;
import Modelo.User;
import io.ProductoApiService;

public class MainActivity extends AppCompatActivity {
    public static final User instanceUsuario = new User();
    public static final Cart instanceCarrito = new Cart();
    public static final String URL = "http://ec2-3-227-239-131.compute-1.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (instanceUsuario.getEmail() != null){
            Button button = findViewById(R.id.registrarPersona);
            button.setVisibility(View.GONE);
            button = findViewById(R.id.iniciarSesionInicio);
            button.setVisibility(View.GONE);
        }
        else {
            Button button = findViewById(R.id.registrarPersona);
            button.setVisibility(View.VISIBLE);
            button = findViewById(R.id.iniciarSesionInicio);
            button.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent i ;
        switch(item.getItemId()){
            case R.id.carrito :
                if (instanceUsuario.getEmail() != null){
                    i = new Intent(this,CarritoActivity.class);
                    startActivity(i);}
                else {
                    Toast toast = Toast.makeText(MainActivity.this, "Debe iniciar sesion si quiere ver su carrito", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.productos :
                i = new Intent(this,ProductosActivity.class);
                startActivity(i);
                break;
            case R.id.cerrarSesionMenu :
                instanceUsuario.setEmail(null);
                Button button = findViewById(R.id.registrarPersona);
                button.setVisibility(View.VISIBLE);
                button = findViewById(R.id.iniciarSesionInicio);
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.quienesSomos:
                i = new Intent(this,QuienesSomosActivity.class);
                startActivity(i);
                break;
            default :

        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        if (instanceUsuario.getEmail() != null) {
            mi.inflate(R.menu.menu, menu);
        }
        else {
            mi.inflate(R.menu.menu2, menu);
        }
        return true;
    }

    public void irAlRegistro(View view){
        Intent i = new Intent(this,RegistrarseActivity.class);
        startActivity(i);
    }
    public void iniciarSesion(View view){
        Intent i = new Intent(this,IniciarSesionActivity.class);
        startActivity(i);
    }

}










