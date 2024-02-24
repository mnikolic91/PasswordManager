package Controller;

import Model.PasswordUtil;
import Model.UserModel;
import Model.UserSession;
import Service.UserService;
import View.LoginPage.LoginPanelListener;
import View.LoginPage.LoginPanel;
import View.RegistrationPage.RegistrationPanelListener;
import View.RegistrationPage.RegistrationPanel;

import javax.swing.*;
import java.util.Arrays;

public class UserController {

    private UserService userService;
    private LoginPanel loginPanel;
    private LoginPanelListener loginListener;
    private RegistrationPanel registrationPanel;
    private RegistrationPanelListener registrationListener;

    public UserController(UserService userService, LoginPanel loginPanel, RegistrationPanel registrationPanel) {
        this.userService = userService;
        this.loginPanel = loginPanel;
        this.registrationPanel = registrationPanel;
        initController();
        initLoginActionListener();

    }

    private void initController() {
         registrationListener = new RegistrationPanelListener() {
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

    private void initLoginActionListener(){
        loginListener = new LoginPanelListener() {
            @Override
            public void loginSucceeded() {
                System.out.println("Login successful reached");
                performLogin(loginPanel.getUsername(), loginPanel.getPassword());
            }
            //useless function which purpose is just to remove a bizarre bug that happens while instancing the listener
            //function shouldnt be removed because it will cause a bug
            public void uselessFunction(){
                System.out.println("Useless function");
            }
        };
        loginPanel.setLoginListener(loginListener);
    }

    private void performLogin(String username, char[] password) {
        System.out.println("Performing login");
        boolean loginSuccess = userService.loginUser(username, new String(password));
        if (loginSuccess) {
            UserModel user = userService.findByUsername(username);
            if (user != null) {
                UserSession.getInstance().setUserID(user.getUserID());
                System.out.println("User ID: " + user.getUserID() + " is now logged in.");
                JOptionPane.showMessageDialog(loginPanel, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
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

        String salt = PasswordUtil.generateSalt(16);
        String hashedPassword = PasswordUtil.hashPassword(new String(password), salt);

        boolean registrationSuccess = userService.registerUser(username, hashedPassword, salt);

        if (registrationSuccess) {
            JOptionPane.showMessageDialog(registrationPanel, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(registrationPanel, "Registration failed. Please try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
