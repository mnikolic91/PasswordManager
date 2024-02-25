package View.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PreviewPanel extends JPanel {
    private JTextField titleField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField urlField;
    private JButton updatePasswordButton;
    private JButton deletePasswordButton;
    private JButton generatePasswordButton;
    private JTextField strongPasswordField;

    public PreviewPanel() {
        titleField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        urlField = new JTextField(20);
        updatePasswordButton = new JButton("Update Password");
        deletePasswordButton = new JButton("Delete Password");
        generatePasswordButton = new JButton("Generate a Strong Password");
        strongPasswordField = new JTextField(20);
        strongPasswordField.setEditable(false);

        setLayout(new GridLayout(6, 2, 5, 5));
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("URL:"));
        add(urlField);
        add(new JLabel("Generated Password:"));
        add(strongPasswordField);
        add(updatePasswordButton);
        add(deletePasswordButton);
        add(generatePasswordButton);

        generatePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String generatedPassword = generateStrongPassword();
                strongPasswordField.setText(generatedPassword);
            }
        });
    }
    private String generateStrongPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+{}[]";

        String allChars = upperCaseLetters + lowerCaseLetters + numbers + specialChars;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }

        return password.toString();
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getURL() {
        return urlField.getText();
    }

    public void setData(String title, String username, String password, String url) {
        titleField.setText(title);
        usernameField.setText(username);
        passwordField.setText(password);
        urlField.setText(url);
    }


}
