package com.example.grupo8_elpaseotelolleva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuienesSomosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quienes_somos);
        TextView textView = findViewById(R.id.textoQuienesSomos);
        textView.setText("Artesanos y Productores que ofrecemos calidad al precio justo. Comercialización directa al consumidor, sin intermediarios");
    }
    public void volverInicio(View v){
        finish();
    }
}