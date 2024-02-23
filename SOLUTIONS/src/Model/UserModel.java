package Model;

public class UserModel {

    private int userID;
    private String username;
    private String password;
    private String salt;

    public UserModel(String username, String hashedPassword, String salt) {
        this.username = username;
        this.password = hashedPassword;
        this.salt = salt;
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


    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
