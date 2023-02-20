package Modelo;

import java.util.List;

public class Product {
    private String title;
    private List<Image> images;
    private List<Category> categories ;
    private String description;
    private float price;
    private long id;
    private int cantidadEnElCarrito=1;
    private Producer producer;

    public Product() {

    }

    public Product(String title, List<Image> images, List<Category> categories, String description, float price, long id, Producer producer) {
        this.setProducer(producer);
        this.setTitle(title);
        this.setImages(images);
        this.setCategories(categories);
        this.setDescription(description);
        this.setPrice(price);
        this.setId(id);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() {
        return images;
        }

    public void setImages(List<Image> images) {
        this.images = images;
      }

    public List<Category> getCategory() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCantidadEnElCarrito() {
        return cantidadEnElCarrito;
    }

    public void setCantidadEnElCarrito(int cantidadEnElCarrito) {
        this.cantidadEnElCarrito = cantidadEnElCarrito;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
