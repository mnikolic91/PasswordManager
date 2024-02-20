package Controller;

import Service.UserService;
import View.LoginPage.LoginPanel;
import View.RegistrationPage.RegistrationListener;
import View.RegistrationPage.RegistrationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UserController {

    private UserService userService;
    private LoginPanel loginPanel;
    private RegistrationPanel registrationPanel;
    private RegistrationListener registrationListener;

    public UserController(UserService userService, LoginPanel loginPanel, RegistrationPanel registrationPanel) {
        this.userService = userService;
        this.loginPanel = loginPanel;
        this.registrationPanel = registrationPanel;
        initController();
    }

    private void initController() {
        loginPanel.addLoginButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        registrationListener = new RegistrationListener() {
            @Override
            public void registrationSucceeded() {
                System.out.println("Registration successful");
                performRegistration(registrationPanel.getUsername(), registrationPanel.getPassword(), registrationPanel.getConfirmPassword());
            }

            @Override
            public void backToLogin() {
            }
        };

        registrationPanel.setRegistrationListener(registrationListener);
    }

    private void performLogin() {
        String username = loginPanel.getUsername();
        char[] password = loginPanel.getPassword();
        boolean loginSuccess = userService.loginUser(username, new String(password));
        if (loginSuccess) {
            JOptionPane.showMessageDialog(loginPanel, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(loginPanel, "Login Failed, Please Try Again", "Failed", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void performRegistration(String username, char[] password, char[] confirmPassword) {
        System.out.println("Performing registration");

        if (!Arrays.equals(password, confirmPassword)) {
            JOptionPane.showMessageDialog(registrationPanel, "Password Mismatch", "Registration failed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean registrationSuccess = userService.registerUser(username, new String(password));

        if (registrationSuccess) {
            JOptionPane.showMessageDialog(registrationPanel, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(registrationPanel, "Please try again.", "Registration failed", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
