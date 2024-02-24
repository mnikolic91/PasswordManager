package Controller;

import Model.PasswordModel;
import Service.PasswordService;
import View.Dashboard.PasswordPanel;

import java.util.List;

public class PasswordController {

    private PasswordService passwordService;
    private PasswordPanel passwordPanel;

    public PasswordController(PasswordPanel passwordPanel) {
        this.passwordService = new PasswordService();
        this.passwordPanel = passwordPanel;
        passwordPanel.setPasswordController(this);
    }

    public void refreshPasswordList() {
        int userID = 1;
        List<PasswordModel> passwords = passwordService.getPasswordsForUser(userID);
        if (passwordPanel != null) {
            System.out.println("Updating password table");
            passwordPanel.updatePasswordTable(passwords);
        }
    }

    public void setPasswordPanel(PasswordPanel passwordPanel) {
        this.passwordPanel = passwordPanel;
    }
}
