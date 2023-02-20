package com.example.grupo8_elpaseotelolleva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import Modelo.Address;
import Modelo.User;
import io.ProductoApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RegistrarseActivity extends AppCompatActivity {
    private ProductoApiService serv;
    private User user = MainActivity.instanceUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);

    }

    public void registrarUsuario(View view){
        EditText editText =  findViewById(R.id.ingresarEmail);
        String email = editText.getText().toString();

        EditText editTextt =  findViewById(R.id.ingresarContra);
        String contraseña = editTextt.getText().toString();

        EditText editTexttt =  findViewById(R.id.ingresarContraNuevamente);
        String contraseñaBis = editTexttt.getText().toString();

        EditText editT =  findViewById(R.id.ingresaNombre);
        String nombrePersona = editT.getText().toString();

        EditText editTe =  findViewById(R.id.ingresaApellido);
        String apellido = editTe.getText().toString();


        EditText edit = findViewById(R.id.ingresaTeléfono);
        String telefono = edit.getText().toString();

        edit = findViewById(R.id.ingresaEdad);
        String edad = edit.getText().toString();
        int edadd;
        if (edad.isEmpty()) {
            edadd=0;
        }
        else{
            edadd = Integer.parseInt(edad);
        }

        //address
        EditText editText2 =  findViewById(R.id.ingresaCalle);
        String calle = editText2.getText().toString();

        EditText edit2 =  findViewById(R.id.ingresaNumero);
        String num = edit2.getText().toString();

        EditText editText3 =  findViewById(R.id.ingresaPiso);
        String piso = editText3.getText().toString();

        EditText editText4 = findViewById(R.id.ingresaDepartamento);
        String depto = editText4.getText().toString();

        EditText editText5 =  findViewById(R.id.ingresaInfoAdicional);
        String desc = editText5.getText().toString();


        if((contraseña.compareTo(contraseñaBis) == 0) && (contraseña.length()>=3) && (nombrePersona.length()>=1) && (apellido.length()>=1) && (telefono.length()>=1) && (num.length()>1) &&(calle.length()>=1) && (email.contains("@") && (edadd != 0))){

            Address address = new Address(calle, num, desc, piso, depto);
            int role=0;
            String descccc= "hola";
            User usuario = new User(nombrePersona,apellido, telefono, contraseña, email, address, nombrePersona, descccc);
            //usuario.setAge(edadd);
            postearUsuario(usuario);
        }
        else {
            if((contraseña.compareTo(contraseñaBis) != 0)){
                Toast toast = Toast.makeText(RegistrarseActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                if((contraseña.length()<3)){
                    Toast toast = Toast.makeText(RegistrarseActivity.this, "La contraseña debe contener como mínimo 3 dígitos.", Toast.LENGTH_SHORT);
                    toast.show();

                }
                else {
                    if((nombrePersona.length()<1) || (apellido.length()<1) || (num.length() < 1 ) || (calle.length()<1) || (telefono.length()<1) || (edadd == 0) ){
                        Toast toast = Toast.makeText(RegistrarseActivity.this, "Completar todos los campos obligatorios.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        if(! email.contains("@")) {
                                Toast toast = Toast.makeText(RegistrarseActivity.this, "El campo email no contiene el formato correcto(falta @)", Toast.LENGTH_SHORT);
                                toast.show();
                        }
                    }
                }
             }
        }
    }

    public void postearUsuario(User usu){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        serv = retrofit.create(ProductoApiService.class);
        serv.postUser(usu).enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                if (response.code() == 500){
                    Toast toast = Toast.makeText(RegistrarseActivity.this, "Error en el servidor", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(RegistrarseActivity.this, "Te has registrado correctamente", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                String msg = t.getMessage();
            }
        });
    }
    public void iniciarSesionDesdeRegistro(View v){
        Intent i = new Intent(this,IniciarSesionActivity.class);
        startActivity(i);
    }
    public void cancelarRegistro(View v){
        finish();
    }

}