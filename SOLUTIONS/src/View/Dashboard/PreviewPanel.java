package View.Dashboard;

import javax.swing.*;
import java.awt.*;

public class PreviewPanel extends JPanel {
    private JTextField titleField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField urlField;
    private JButton updatePasswordButton;
    private JButton deletePasswordButton;
    private JButton generatePasswordButton;

    public PreviewPanel() {
        titleField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        urlField = new JTextField(20);
        updatePasswordButton = new JButton("Update Password");
        deletePasswordButton = new JButton("Delete Password");
        generatePasswordButton = new JButton("Generate a Strong Password");

        setLayout(new GridLayout(5, 2, 5, 5)); // Adjust grid layout as per your need
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("URL:"));
        add(urlField);
        add(updatePasswordButton);
        add(deletePasswordButton);
        add(generatePasswordButton);
    }

    // Add getters for accessing the data in the input fields
    public String getTitle() {
        return titleField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        // getPassword() returns a char array, convert it to a string
        return new String(passwordField.getPassword());
    }

    public String getURL() {
        return urlField.getText();
    }

    // Method to set data in the input fields
    public void setData(String title, String username, String password, String url) {
        titleField.setText(title);
        usernameField.setText(username);
        passwordField.setText(password);
        urlField.setText(url);
    }

    // Method to clear data in the input fields
    public void clearData() {
        titleField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        urlField.setText("");
    }
}
