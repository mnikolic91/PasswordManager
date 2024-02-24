package View.LoginPage;

import View.Dashboard.DashboardPanel;
import View.PanelChangeListener;
import View.RegistrationPage.RegistrationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPanel extends JPanel {

    private PanelChangeListener panelChangeListener;
    private LoginPanelListener loginListener;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel registerLabel;
    private JLabel logoName;
    private ImageIcon logoIcon;
    private JLabel logoLabel;

    public LoginPanel(PanelChangeListener panelChangeListener) {
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
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        registerLabel = new JLabel("Don't have an account?");
        logoName = new JLabel("Keep It Safe");
        logoIcon = new ImageIcon("imgs/logoicon.png");
        logoLabel = new JLabel(logoIcon);

        usernameField.setMaximumSize(usernameField.getPreferredSize());
        passwordField.setMaximumSize(passwordField.getPreferredSize());

        setComponentForeground(Color.WHITE);

    }

    private void layoutUpperComps() {
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

        JPanel loginButtonPanel = createPanel();
        loginButtonPanel.add(loginButton);
        centerPanel.add(loginButtonPanel);

        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }

    private void layoutBottomComps() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(registerLabel);
        bottomPanel.add(registerButton);
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
        registerLabel.setForeground(color);
        usernameField.setCaretColor(Color.BLACK);
        passwordField.setCaretColor(Color.BLACK);
        loginButton.setForeground(color);
        loginButton.setBackground(Color.GRAY);
        registerButton.setForeground(color);
        registerButton.setBackground(Color.DARK_GRAY);
    }

    private void activateComps() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login button clicked");
                if (loginListener != null) {
                    System.out.println("Login listener is not null");
                    loginListener.loginSucceeded();
                    DashboardPanel dashboardPanel = new DashboardPanel(panelChangeListener);
                    dashboardPanel.setVisible(true);
                    panelChangeListener.onPanelChange(dashboardPanel);
                } else {
                    System.out.println("Login listener is null");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelChangeListener != null) {
                    RegistrationPanel registrationPanel = new RegistrationPanel(panelChangeListener);
                    panelChangeListener.onPanelChange(registrationPanel);
                }
            }
        });
    }


    public void setLoginListener(LoginPanelListener listener) {
        this.loginListener = listener;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }


}

