package com.example.grupo8_elpaseotelolleva;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Modelo.Product;
import Modelo.User;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private List<Product> list;
    private Context context;
    private ArrayList<Product> productosDelCarrito = MainActivity.instanceCarrito.getProductos();
    private User user = MainActivity.instanceUsuario;

    public MyCustomAdapter(List<Product> list, Context c) {
        this.list = list;
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
            view = inflater.inflate(R.layout.custom_layout, null);
        }

        TextView nombreProd = view.findViewById(R.id.nombreProducto);
        String s = list.get(position).getTitle();
        if (s.length() > 20){
            nombreProd.setText(s.substring(0,20) + "\n" +s.substring(20,s.length()) );
        }
        else {
            nombreProd.setText(s);
        }

        TextView precioProd= view.findViewById(R.id.precioProducto);
        precioProd.setText("$" + ((list.get(position).getPrice())));


        Button agregarbtn= (Button)view.findViewById(R.id.agregarAlCarrito);
        Button addBtn= (Button)view.findViewById(R.id.agregarAlCarrito);

        Button verPordbtn= (Button)view.findViewById(R.id.verProd);
        Button addBtn2= (Button)view.findViewById(R.id.verProd);

        agregarbtn.setOnClickListener(v -> {

        });
        addBtn.setOnClickListener(v -> {
            if (user.getEmail() != null){
                agregarAlCarrito(position);
                Toast toast = Toast.makeText(context, "Producto agregado", Toast.LENGTH_SHORT);
                toast.show();
                notifyDataSetChanged();}
            else {
                Toast toast = Toast.makeText(context, "Debe iniciar sesion si quiere agregar a su carrito", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
        verPordbtn.setOnClickListener(v -> {


        });
        addBtn2.setOnClickListener(v -> {
            if (user.getEmail() != null){
                context.startActivity(new Intent(context, DetalleProductoActivity.class).
                        putExtra("idProdcuto",list.get(position).getId()));
                notifyDataSetChanged();}
            else {
                Toast toast = Toast.makeText(context, "Debe iniciar sesion si quiere ver este producto", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }
    public void agregarAlCarrito(int p){
        boolean b = false;
        for (Product pr : productosDelCarrito){
            if (pr.getId() == list.get(p).getId()){
                b=true;
                pr.setCantidadEnElCarrito(pr.getCantidadEnElCarrito() + 1);
            }
        }
        if (! b){
            productosDelCarrito.add(list.get(p));
        }
    }

}
