package Controller;

import Model.PasswordModel;
import Model.PasswordSession;
import Model.UserSession;
import ObserverInterfaces.ObservableInterface;
import ObserverInterfaces.ObserverInterface;
import Service.PasswordService;
import View.Dashboard.MenuPanel;
import View.Dashboard.PasswordPanel;
import View.Dashboard.PreviewPanel;

import java.util.List;

public class PasswordController implements ObserverInterface, PasswordUpdateListener {

    private PasswordSession passwordSession;
    private PasswordService passwordService;
    private PasswordPanel passwordPanel;
    private MenuPanel menuPanel;
    private PreviewPanel previewPanel;
    private int userID;

    public PasswordController(PasswordPanel passwordPanel, MenuPanel menuPanel, PreviewPanel previewPanel) {
        this.passwordService = new PasswordService();
        this.passwordSession = new PasswordSession();
        this.passwordSession.addObserver(this);
        this.passwordPanel = passwordPanel;
        this.menuPanel = menuPanel;
        this.previewPanel = previewPanel;


        userID = UserSession.getInstance().getUserID();
        refreshPasswordList();
        displayFirstPassword();
        setupPasswordSelectionListener();

        this.menuPanel.setRefreshAction(e -> refreshPasswordList());
        previewPanel.setUpdateListener(this);
    }

    private void setupPasswordSelectionListener() {
        passwordPanel.addPasswordSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int viewRow = passwordPanel.getPasswordTable().getSelectedRow();
                if (viewRow >= 0) {
                    int modelRow = passwordPanel.getPasswordTable().convertRowIndexToModel(viewRow);
                    int passwordId = (int) passwordPanel.getPasswordTable().getModel().getValueAt(modelRow, 0);
                    System.out.println("Selected Password ID: " + passwordId);
                    passwordSession.setSelectedPasswordId(passwordId);
                }
            }
        });
    }

    public void refreshPasswordList() {
        userID = UserSession.getInstance().getUserID();
        List<PasswordModel> passwords = passwordService.getPasswordsForUser(userID);
        System.out.println("Refreshing password list for user ID: " + userID);
        if (passwordPanel != null) {
            System.out.println("Updating password table for user ID: " + userID);
            passwordPanel.updatePasswordTable(passwords);
        }
    }

    public void addNewPassword(String title, String username, String password, String url) {
        userID = UserSession.getInstance().getUserID();
        PasswordModel newPassword = new PasswordModel(userID, title, username, password, url);
        passwordService.addNewPassword(newPassword);
        refreshPasswordList();
    }

    private void displayFirstPassword() {
        userID = UserSession.getInstance().getUserID();
        List<PasswordModel> passwords = passwordService.getPasswordsForUser(userID);
        if (!passwords.isEmpty()) {
            PasswordModel firstPassword = passwords.get(0);
            previewPanel.setData(firstPassword.getTitle(), firstPassword.getUsername(), firstPassword.getPassword(), firstPassword.getUrl());
        }
    }

    public void updatePassword(int passwordId, String title, String username, String password, String url) {
        PasswordModel updatedPassword = new PasswordModel(userID, title, username, password, url);
        updatedPassword.setEntryID(passwordId);
        passwordService.updatePassword(updatedPassword);
        refreshPasswordList();
    }


    @Override
    public void update(ObservableInterface subject, Object arg) {
        if (subject instanceof PasswordSession) {
            int passwordId = (int) arg;
            PasswordModel password = passwordService.getPasswordById(passwordId);
            previewPanel.setSelectedPasswordId(passwordId);
            if (password != null) {
                previewPanel.setData(password.getTitle(), password.getUsername(), password.getPassword(), password.getUrl());
            }
        }
    }

    @Override
    public void onUpdateRequested(int passwordId, String title, String username, String password, String url) {
        updatePassword(passwordId, title, username, password, url);
    }
}