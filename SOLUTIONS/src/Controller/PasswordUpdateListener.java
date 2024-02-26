package Controller;

public interface PasswordUpdateListener {
    void onUpdateRequested(int passwordId, String title, String username, String password, String url);
}
