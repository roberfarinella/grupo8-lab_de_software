package Modelo;

import java.util.ArrayList;

public class Cart {
    private Integer totalElements;
    private ArrayList<Product> productos = new ArrayList<>();

    public Cart() {
    }


    public ArrayList<Product> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Product> productos) {
        this.productos = productos;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }
}
