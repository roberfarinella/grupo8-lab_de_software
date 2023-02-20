package Modelo;

public class Image {
    private Long id;
    private String value;
    private String type;

    public Image(){}

    public Image(Long unId, String unValue, String unType){
        this.setId(unId);
        this.setValue(unValue);
        this.setType(unType);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
