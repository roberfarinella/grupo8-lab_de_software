package Modelo;

public class UserLogIn {
    private String userName;
    private String userPassword;

    public UserLogIn(String user, String pass){
        this.setUserName(user);
        this.setUserPassword(pass);
    }

    public UserLogIn(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
