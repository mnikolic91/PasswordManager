package Controller;

import Model.PasswordModel;
import Model.UserSession;
import Service.PasswordService;
import View.Dashboard.PasswordPanel;

import java.util.List;

public class PasswordController {

    private PasswordService passwordService;
    private PasswordPanel passwordPanel;

    public PasswordController(PasswordPanel passwordPanel) {
        this.passwordPanel = passwordPanel;
        this.passwordService = new PasswordService();
        refreshPasswordList();
    }

    public void refreshPasswordList() {
        int userID = UserSession.getInstance().getUserID();
        List<PasswordModel> passwords = passwordService.getPasswordsForUser(userID);
        if (passwordPanel != null) {
            System.out.println("Updating password table for user ID: " + userID);
            passwordPanel.updatePasswordTable(passwords);
        }
    }
}
