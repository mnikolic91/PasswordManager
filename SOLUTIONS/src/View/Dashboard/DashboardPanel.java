package View.Dashboard;

import Controller.PasswordController;
import Model.DataAccessObjects.PasswordDAO;
import Model.DataAccessObjects.PasswordDAOImplementation;
import Service.PasswordService;
import View.PanelChangeListener;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private PanelChangeListener panelChangeListener;
    private PasswordPanel passwordPanel;
    private MenuPanel menuPanel;
    private PreviewPanel previewPanel;
    private PasswordController passwordController;
    private PasswordDAO passwordDAO;
    private PasswordService passwordService;

    public DashboardPanel(PanelChangeListener panelChangeListener) {
        this.panelChangeListener = panelChangeListener;
        passwordPanel = new PasswordPanel();
        previewPanel = new PreviewPanel();
        menuPanel = new MenuPanel();
        passwordDAO = new PasswordDAOImplementation();
        passwordService = new PasswordService(passwordDAO);
        passwordController = new PasswordController(passwordService, passwordPanel, menuPanel, previewPanel);

        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel(null);
        passwordPanel.setBounds(20, 50, 500, 600);
        previewPanel.setBounds(600, -50, 600, 800);
        contentPanel.add(passwordPanel);
        contentPanel.add(previewPanel);

        add(contentPanel, BorderLayout.CENTER);



        JButton addNewButton = menuPanel.getAddNewButton();
        addNewButton.addActionListener(e -> showAddNewPasswordDialog());
    }

    private void showAddNewPasswordDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add New Password");
        dialog.setSize(1200, 800);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField urlField = new JTextField();
        JButton saveButton = new JButton("Save");

        dialog.add(new JLabel("Title:"));
        dialog.add(titleField);
        dialog.add(new JLabel("Username:"));
        dialog.add(usernameField);
        dialog.add(new JLabel("Password:"));
        dialog.add(passwordField);
        dialog.add(new JLabel("URL:"));
        dialog.add(urlField);
        dialog.add(new JLabel());
        dialog.add(saveButton);

        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String url = urlField.getText();
            passwordController.addNewPassword(title, username, password, url);
            dialog.dispose();
        });

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}