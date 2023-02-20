package com.example.grupo8_elpaseotelolleva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConfiguracionPago extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_pago);
    }

    public void compraFinalizada(View view){
        Toast toast = Toast.makeText(ConfiguracionPago.this, "Compra finalizada.En breve le enviaremos un email con más información.", Toast.LENGTH_SHORT);
        toast.show();
    }




}