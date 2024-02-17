package View;
import View.LoginPage.LoginListener;
import View.LoginPage.LoginPanel;
import View.RegistrationPage.RegistrationListener;
import View.RegistrationPage.RegistrationPanel;

import javax.swing.*;

public class MainFrame extends JFrame implements PanelChangeListener {

    private LoginPanel loginPanel;
    private RegistrationPanel registrationPanel;

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
        loginPanel = new LoginPanel(this);
        loginPanel.setLoginListener(new LoginListener() {

            @Override
            public void loginSucceeded() {
                System.out.println("Login successful. Switching panels...");
            }
        });

        registrationPanel = new RegistrationPanel(this);
        registrationPanel.setRegistrationListener(new RegistrationListener() {
            @Override
            public void registrationSucceeded() {
                System.out.println("Registration successful. Switching panels...");
            }

            @Override
            public void backToLogin() {

            }
        });
    }

    @Override
    public void onPanelChange(JPanel newPanel) {
        getContentPane().removeAll();
        getContentPane().add(newPanel);
        revalidate();
        repaint();
    }



    private void layoutComps() {
        add(loginPanel);
    }


}
