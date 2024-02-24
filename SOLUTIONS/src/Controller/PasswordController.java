package Controller;

import Model.PasswordModel;
import Model.UserSession;
import Service.PasswordService;
import View.Dashboard.MenuPanel;
import View.Dashboard.PasswordPanel;

import java.util.List;

public class PasswordController {

    private PasswordService passwordService;
    private PasswordPanel passwordPanel;
    private MenuPanel menuPanel;
    private int userID;

    public PasswordController(PasswordPanel passwordPanel, MenuPanel menuPanel) {
        this.passwordPanel = passwordPanel;
        this.menuPanel = menuPanel;
        this.passwordService = new PasswordService();

        userID = UserSession.getInstance().getUserID();
        refreshPasswordList();
    }

    public void refreshPasswordList() {
        List<PasswordModel> passwords = passwordService.getPasswordsForUser(userID);
        System.out.println("Refreshing password list for user ID: " + userID);
        if (passwordPanel != null) {
            System.out.println("Updating password table for user ID: " + userID);
            passwordPanel.updatePasswordTable(passwords);
        }
    }

    public void addNewPassword(String title, String username, String password, String url) {
        PasswordModel newPassword = new PasswordModel(userID, title, username, password, url);
        passwordService.addNewPassword(newPassword);
        refreshPasswordList();
    }
}
