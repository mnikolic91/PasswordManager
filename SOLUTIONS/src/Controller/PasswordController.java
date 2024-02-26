package Controller;

import Model.PasswordModel;
import Model.PasswordSession;
import Model.PasswordUtil;
import Model.UserSession;
import ObserverInterfaces.ObservableInterface;
import ObserverInterfaces.ObserverInterface;
import Service.PasswordService;
import View.Dashboard.EncryptionStrategy.EncryptionStrategy;
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


    @Override
    public void update(ObservableInterface subject, Object arg) {
        if (subject instanceof PasswordSession) {
            int passwordId = (int) arg;
            PasswordModel password = passwordService.getPasswordById(passwordId);
            previewPanel.setSelectedPasswordId(passwordId);
            if (password != null) {
                String encryptionType = password.getEncryptionType();
                previewPanel.setData(password.getTitle(), password.getUsername(), password.getPassword(), password.getUrl(), encryptionType); // Sada proslijedite i peti argument
            }
        }
    }


    @Override
    public void onUpdateRequested(int passwordId, String title, String username, String password, String url) {
        EncryptionStrategy strategy = menuPanel.getCurrentEncryptionStrategy();
        String encryptionType = null;

        if (strategy != null) {
            password = strategy.encrypt(password);
            encryptionType = strategy.getClass().getSimpleName();
        } else {
            encryptionType = "PLAIN_TEXT";
        }

        PasswordModel updatedPassword = new PasswordModel(userID, title, username, password, url);
        updatedPassword.setEntryID(passwordId);
        updatedPassword.setEncryptionType(encryptionType);
        passwordService.updatePassword(updatedPassword);
        refreshPasswordList();
    }

    @Override
    public void onDeleteRequested(int passwordId) {
        int userId = UserSession.getInstance().getUserID();
        passwordService.deletePassword(passwordId, userId);
        refreshPasswordList();
    }


}