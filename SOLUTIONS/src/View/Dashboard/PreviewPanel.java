package View.Dashboard;

import Controller.PasswordUpdateListener;
import View.Dashboard.EncryptionStrategy.CaesarCipherStrategy;
import View.Dashboard.EncryptionStrategy.EncryptionStrategy;
import View.Dashboard.EncryptionStrategy.XOREncryptionStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PreviewPanel extends JPanel {
    private JTextField titleField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField urlField;
    private JButton updatePasswordButton;
    private JButton deletePasswordButton;
    private JButton generatePasswordButton;
    private JTextField strongPasswordField;
    private PasswordUpdateListener updateListener;

    private int selectedPasswordId = -1;


    public PreviewPanel() {
        titleField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        urlField = new JTextField(20);
        strongPasswordField = new JTextField(20);
        updatePasswordButton = new JButton("Update Password");
        deletePasswordButton = new JButton("Delete Password");
        generatePasswordButton = new JButton("Generate a Strong Password");
        strongPasswordField.setEditable(false);


        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(50, 50, 50, 50);
        add(new JLabel("Title:"), gc);
        gc.gridx = 1;
        add(titleField, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        add(new JLabel("Username:"), gc);
        gc.gridx = 1;
        add(usernameField, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        add(new JLabel("Password:"), gc);
        gc.gridx = 1;
        add(passwordField, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        add(new JLabel("URL:"), gc);
        gc.gridx = 1;
        add(urlField, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        add(generatePasswordButton, gc);
        gc.gridx = 1;
        add(strongPasswordField, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        add(updatePasswordButton, gc);
        gc.gridx = 1;
        add(deletePasswordButton, gc);


        generatePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String generatedPassword = generateStrongPassword();
                strongPasswordField.setText(generatedPassword);
            }
        });

        updatePasswordButton.addActionListener(e -> {
            if (selectedPasswordId != -1) {
                String title = titleField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String url = urlField.getText();
                String generatedPassword = strongPasswordField.getText();

                if (!generatedPassword.isEmpty() && !password.equals(generatedPassword)) {
                    password = generatedPassword;
                }

                updateListener.onUpdateRequested(selectedPasswordId, title, username, password, url);
            } else {
                JOptionPane.showMessageDialog(null, "No password selected for update.");
            }
        });
    }

    public void setSelectedPasswordId(int id) {
        this.selectedPasswordId = id;
    }


    public void setUpdateListener(PasswordUpdateListener listener) {
        this.updateListener = listener;
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


    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setData(String title, String username, String password, String url, String encryptionType) {
        titleField.setText(title);
        usernameField.setText(username);
        passwordField.setText(decryptPassword(password, encryptionType));
        urlField.setText(url);
    }

    private String decryptPassword(String encryptedPassword, String encryptionType) {
        EncryptionStrategy strategy = getEncryptionStrategy(encryptionType);
        if (strategy != null) {
            return strategy.decrypt(encryptedPassword);
        }
        return encryptedPassword;
    }

    private EncryptionStrategy getEncryptionStrategy(String encryptionType) {
        if ("Caesar".equals(encryptionType)) {
            return new CaesarCipherStrategy();
        } else if ("XORE".equals(encryptionType)) {
            return new XOREncryptionStrategy();
        }
        return null;
    }
}