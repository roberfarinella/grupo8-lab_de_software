package Modelo;

public class Address {
    private Integer id;
    private String  street;
    private String number;
    private String description;
    //private Double latitude;
   // private Double longitude;
    private String floor;
    private String  apartment;
    private String  betweenStreets;


    public Address(){}

    public Address(Integer id, String street, String number, String description, /*Double latitude, Double longitude,*/ String unFloor, String unApart, String unasBet) {
        this.setId(id);
        this.setStreet(street);
        this.setNumber(number);
        this.setDescription(description);
       //this.setLatitude(latitude);
       // this.setLongitude(longitude);
        this.setFloor(unFloor);
        this.setApartment(unApart);
        this.setBetweenStreets(unasBet);
    }

    public Address(String street,String number, String description,String unFloor, String unApart  ){
        this.setFloor(unFloor);
        this.setApartment(unApart);
        this.setStreet(street);
        this.setNumber(number);
        this.setDescription(description);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }*/

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBetweenStreets() {
        return betweenStreets;
    }

    public void setBetweenStreets(String betweenStreets) {
        this.betweenStreets = betweenStreets;
    }
}
