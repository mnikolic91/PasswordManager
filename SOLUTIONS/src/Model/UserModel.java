package Model;

public class UserModel {

    private int userID;
    private String username;
    private String password;
    private String email;

    public UserModel(String username, String password, String email) {
        userID++;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
