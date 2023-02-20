package com.example.grupo8_elpaseotelolleva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import Modelo.Token;
import Modelo.User;
import Modelo.UserLogIn;
import io.ProductoApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class IniciarSesionActivity extends AppCompatActivity {
    private ProductoApiService serv;
    private User user = MainActivity.instanceUsuario;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sesion);

    }

    public void logIn(View v){
        dialog=new ProgressDialog(this);
        dialog.setMessage("Iniciando sesion...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        EditText et = findViewById(R.id.ingresarNombre);
        String userName = et.getText().toString();

        et = findViewById(R.id.ingresarContraseña);
        String password = et.getText().toString();

        if (userName != null && password !=null){
            UserLogIn userLogIn = new UserLogIn(userName,password);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.URL)
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .build();

            serv = retrofit.create(ProductoApiService.class);
            serv.getToken(userLogIn).enqueue(new Callback<Token>(){
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Token token = response.body();
                    if ( token != null){
                        user.setEmail(token.getUser().getEmail());
                        user.setToken(token.getValue());
                        dialog.hide();
                        Toast toast = Toast.makeText(IniciarSesionActivity.this, "Usted se ha iniciado correctamente", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(IniciarSesionActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        dialog.hide();
                        Toast toast = Toast.makeText(IniciarSesionActivity.this, "El usuario o contraseña es incorrecto", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {

                    String msg = t.getMessage();
                }
            });
        }
        else {
            Toast toast = Toast.makeText(IniciarSesionActivity.this, "Algún campo se encuentra incompleto.", Toast.LENGTH_SHORT);
            toast.show();
        }



    }

    public void cancelarInicioSesion(View v){
        finish();
    }

    public void registrarse(View v){
        Intent i = new Intent(this,RegistrarseActivity.class);
        startActivity(i);
    }
}
