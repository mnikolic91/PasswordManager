package View;
import Controller.UserController;
import Model.DataAccessObjects.UserDAO;
import Model.DataAccessObjects.UserDAOImplementation;
import Service.UserService;
import View.LoginPage.LoginPanel;
import View.RegistrationPage.RegistrationPanel;

import javax.swing.*;

public class MainFrame extends JFrame implements PanelChangeListener {

    private LoginPanel loginPanel;
    private RegistrationPanel registrationPanel;
    private UserController userController;

    public MainFrame() {
        setTitle("Simple Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        initComps();
        layoutComps();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComps() {
        loginPanel = new LoginPanel(this);
        registrationPanel = new RegistrationPanel(this);

        UserDAO userDAO = new UserDAOImplementation();
        UserService userService = new UserService(userDAO);

        userController = new UserController(userService, loginPanel, registrationPanel);
    }

    @Override
    public void onPanelChange(JPanel newPanel) {
        System.out.println("Changing panel...");
        getContentPane().removeAll();
        getContentPane().add(newPanel);
        revalidate();
        repaint();
    }

    private void layoutComps() {
        add(registrationPanel);
        add(loginPanel);

    }
}
