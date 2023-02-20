package Modelo;

public class Token {
    private User user;
    private String value;

    public Token(User user, String value) {
        this.setUser(user);
        this.setValue(value);
    }

    public Token(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
