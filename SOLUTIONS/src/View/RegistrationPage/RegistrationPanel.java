package View.RegistrationPage;

import View.LoginPage.LoginPanel;
import View.PanelChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPanel extends JPanel {

    private PanelChangeListener panelChangeListener;
    private RegistrationPanelListener registrationListener;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton backToLoginButton;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel registerLabel;
    private JLabel logoName;
    private ImageIcon logoIcon;
    private JLabel logoLabel;

    public RegistrationPanel(PanelChangeListener panelChangeListener) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        initComps();
        layoutUpperComps();
        layoutCenterComps();
        layoutBottomComps();
        activateComps();

        this.panelChangeListener = panelChangeListener;

    }

    private void initComps() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);
        backToLoginButton = new JButton("Back to Login");
        registerButton = new JButton("Register");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm Password:");
        registerLabel = new JLabel("Already have an account?");
        logoName = new JLabel("Keep It Safe");
        logoIcon = new ImageIcon("imgs/logoicon.png");
        logoLabel = new JLabel(logoIcon);

        usernameField.setMaximumSize(usernameField.getPreferredSize());
        passwordField.setMaximumSize(passwordField.getPreferredSize());
        confirmPasswordField.setMaximumSize(confirmPasswordField.getPreferredSize());

        setComponentForeground(Color.WHITE);

    }

    private void layoutUpperComps(){
        JPanel upperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        upperPanel.setOpaque(false);
        logoName.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
        upperPanel.add(logoName);
        upperPanel.add(logoLabel);
        add(upperPanel, BorderLayout.PAGE_START);
    }

    private void layoutCenterComps() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.add(Box.createVerticalGlue());

        JPanel usernamePanel = createPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        usernamePanel.add(usernameField);
        centerPanel.add(usernamePanel);

        JPanel passwordPanel = createPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        passwordPanel.add(passwordField);
        centerPanel.add(passwordPanel);

        JPanel confirmPasswordPanel = createPanel();
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        confirmPasswordPanel.add(confirmPasswordField);
        centerPanel.add(confirmPasswordPanel);
        centerPanel.add(registerButton);

        centerPanel.add(Box.createVerticalGlue());
        add(centerPanel, BorderLayout.CENTER);
    }

    private void layoutBottomComps() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(registerLabel);
        bottomPanel.add(backToLoginButton);

        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setOpaque(false);
        return panel;
    }

    private void setComponentForeground(Color color) {
        logoName.setForeground(color);
        usernameLabel.setForeground(color);
        passwordLabel.setForeground(color);
        confirmPasswordLabel.setForeground(color);
        registerLabel.setForeground(color);
        usernameField.setCaretColor(color.BLACK);
        passwordField.setCaretColor(color.BLACK);
        confirmPasswordField.setCaretColor(color.BLACK);
        backToLoginButton.setForeground(color);
        backToLoginButton.setBackground(Color.DARK_GRAY);
        registerButton.setForeground(color);
        registerButton.setBackground(Color.GRAY);
    }


    private void activateComps() {
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (panelChangeListener != null) {
                    LoginPanel loginPanel = new LoginPanel(panelChangeListener);
                    panelChangeListener.onPanelChange(loginPanel);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("register button clicked");
                if (registrationListener != null) {
                    registrationListener.registrationSucceeded();
                    System.out.println("not null");

                }
            }
        });
    }

    public void setRegistrationListener(RegistrationPanelListener listener) {
        this.registrationListener = listener;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public char[] getConfirmPassword() {
        return confirmPasswordField.getPassword();
    }
}
