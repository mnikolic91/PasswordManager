package Model;

public class PasswordModel {

    private static int entryID;
    private int userID;
    private String title;
    private String username;
    private String password;
    private String url;
    private String creationDate;
    private String lastUpdateDate;

    public PasswordModel(int userID, String title, String username, String password, String url, String creationDate, String lastUpdateDate) {
        entryID++;
        this.userID = userID;
        this.title = title;
        this.username = username;
        this.password = password;
        this.url = url;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public static int getEntryID() {
        return entryID;
    }

    public static void setEntryID(int entryID) {
        PasswordModel.entryID = entryID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
