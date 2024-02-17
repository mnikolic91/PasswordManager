package View;
import View.LoginPage.LoginListener;
import View.LoginPage.LoginPanel;

import javax.swing.*;

public class MainFrame extends JFrame {

    private LoginPanel loginPanel;

    public MainFrame() {
        setTitle("Login page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        initComps();
        layoutComps();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComps() {
        loginPanel = new LoginPanel();
        loginPanel.setLoginListener(new LoginListener() {

            @Override
            public void loginSucceeded() {
                System.out.println("Login successful. Switching panels...");
            }
        });
    }

    private void layoutComps() {
        add(loginPanel);
    }


}
