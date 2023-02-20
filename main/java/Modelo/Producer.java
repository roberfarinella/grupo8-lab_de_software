package Modelo;

public class Producer {
    private String name;
    private long id;
    private String lastName;
    private String email;
    private String description;
    private Image image;
    private String address;

    public Producer(){}

    public Producer(String name, long id, String lastName,String email,String description,Image image,String address) {
        this.setName(name);
        this.setId(id);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setDescription(description);
        this.setImages(image);
        this.setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImages() {
        return image;
    }

    public void setImages(Image images) {
        this.image = images;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
