package Modelo;

public class Node {
    private Long id;
    private String name;
    private Address address;

    public Node(long id, String name,Address ad) {
        this.setId(id);
        this.setName(name);
        this.setAddress(ad);
    }

    public Node(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
