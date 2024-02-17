package View.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel registerLabel;
    private LoginListener loginListener;
    private JLabel logoName;
    private ImageIcon logoIcon;
    private JLabel logoLabel;

    public LoginPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        initComps();
        layoutUpperComps();
        layoutCenterComps();
        layoutBottomComps();
        activateComps();
    }

    private void initComps() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        registerLabel = new JLabel("Don't have an account?");
        logoName = new JLabel("Simple Password Manager");
        logoIcon = new ImageIcon("imgs/logoicon.png");
        logoLabel = new JLabel(logoIcon);

        usernameField.setMaximumSize(usernameField.getPreferredSize());
        passwordField.setMaximumSize(passwordField.getPreferredSize());

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
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                boolean loginSuccess = attemptLogin(username, password);
                if (loginSuccess) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                    if (loginListener != null) {
                        loginListener.loginSucceeded();
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }


    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    private boolean attemptLogin(String username, char[] password) {
        return true;
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}

