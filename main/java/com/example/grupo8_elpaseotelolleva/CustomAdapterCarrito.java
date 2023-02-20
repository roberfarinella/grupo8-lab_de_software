package com.example.grupo8_elpaseotelolleva;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.ListAdapter;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Modelo.Product;

public class CustomAdapterCarrito extends BaseAdapter implements ListAdapter {
    private Context context;
    private ArrayList<Product> list = MainActivity.instanceCarrito.getProductos();
    private ArrayList<Product> productosDelCarrito = MainActivity.instanceCarrito.getProductos();

    public CustomAdapterCarrito( Context c) {
        this.context = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout_carrito, null);
        }

        TextView nombreProd= view.findViewById(R.id.nombreProductoCarrito);
        String s = list.get(position).getTitle();
        if (s.length() > 19){
            nombreProd.setText(s.substring(0,19) + "\n" +s.substring(19,s.length()) );
        }
        else {
            nombreProd.setText(s);
        }

        TextView precioProd= view.findViewById(R.id.precioProductoCarrito);
        precioProd.setText("$"+(list.get(position).getPrice()));

        TextView cantidadProd= view.findViewById(R.id.cantidadDeProductoCarrito);
        cantidadProd.setText(String.valueOf(list.get(position).getCantidadEnElCarrito()));



        ImageButton btn1= view.findViewById(R.id.eliminarDelCarrito);
        ImageButton eliminarBtn= view.findViewById(R.id.eliminarDelCarrito);

        ImageButton btn2= view.findViewById(R.id.aumentarCantidad);
        ImageButton aumentarBtn= view.findViewById(R.id.aumentarCantidad);

        ImageButton btn3= view.findViewById(R.id.disminuirCantidad);
        ImageButton disminuirBtn= view.findViewById(R.id.disminuirCantidad);

        btn1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    });
        eliminarBtn.setOnClickListener(v -> {
            eliminarAlCarrito(position);
            Toast toast = Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT);
            toast.show();
            context.startActivity(new Intent(context, CarritoActivity.class));
            notifyDataSetChanged();
        });
        btn2.setOnClickListener(v -> {


        });
        aumentarBtn.setOnClickListener(v -> {
            aumentarCantidad(position);
            context.startActivity(new Intent(context, CarritoActivity.class));
            notifyDataSetChanged();
        });
        btn3.setOnClickListener(v -> {


        });
        disminuirBtn.setOnClickListener(v -> {
            disminuirCantidad(position);
            context.startActivity(new Intent(context, CarritoActivity.class));
            notifyDataSetChanged();
        });

        return view;


    }
    private void eliminarAlCarrito(int p){
        for(int i=0; i<productosDelCarrito.size();i=i+1){
            if (productosDelCarrito.get(i).getId() == list.get(p).getId()){
                productosDelCarrito.remove(i);
                i = productosDelCarrito.size();
            }
        }
    }
    private void disminuirCantidad(int p){
        for(int i=0; i<productosDelCarrito.size();i=i+1){
            if (productosDelCarrito.get(i).getId() == list.get(p).getId()){
                int cant = productosDelCarrito.get(i).getCantidadEnElCarrito();
                if (cant > 0){
                    productosDelCarrito.get(i).setCantidadEnElCarrito(cant - 1);
                }
                else {
                    Toast toast = Toast.makeText(context, "La cantidad ya es 0, no se contabiliza en el total", Toast.LENGTH_SHORT);
                    toast.show();
                }
                i = productosDelCarrito.size();
            }
        }
    }
    private void aumentarCantidad(int p){
        for(int i=0; i<productosDelCarrito.size();i=i+1){
            if (productosDelCarrito.get(i).getId() == list.get(p).getId()){
                int cant = productosDelCarrito.get(i).getCantidadEnElCarrito();
                productosDelCarrito.get(i).setCantidadEnElCarrito(cant + 1);
                i = productosDelCarrito.size();
            }
        }
    }
}

