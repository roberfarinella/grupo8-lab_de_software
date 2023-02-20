package Modelo;

import android.media.Image;

public class Page {
    private Long id;
    private Integer   role;
    private  String  firstName;
    private String   lastName;
    private  String  name;
    private  String   encryptedPassword;
    private  String  email;
    private  String  description;
    private Image image;
    private String   phone;
    private  Address  address;

    public Page(){}

    public Page(Long id, Integer role, String firstName, String lastName, String name, String encryptedPassword, String email, String description, Image image, String phone, Address address) {
        this.setId(id);
        this.setRole(role);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setName(name);
        this.setEncryptedPassword(encryptedPassword);
        this.setEmail(email);
        this.setDescription(description);
        this.setImage(image);
        this.setPhone(phone);
        this.setAddress(address);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
