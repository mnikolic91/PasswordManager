package View;
import Controller.UserController;
import Model.DataAccessObjects.UserDAO;
import Model.DataAccessObjects.UserDAOImplementation;
import Service.UserService;

import View.Dashboard.DashboardPanel;
import View.LoginPage.LoginPanel;
import View.RegistrationPage.RegistrationPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements PanelChangeListener {

    private JPanel cards;
    private LoginPanel loginPanel;
    private RegistrationPanel registrationPanel;
    private DashboardPanel dashboardPanel;
    private UserController userController;

    public MainFrame() {
        setTitle("Simple Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        initComps();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComps() {
        cards = new JPanel(new CardLayout());

        registrationPanel = new RegistrationPanel(this);
        loginPanel = new LoginPanel(this);
        dashboardPanel = new DashboardPanel(this);

        cards.add(loginPanel, "LoginPanel");
        cards.add(registrationPanel, "RegistrationPanel");
        cards.add(dashboardPanel, "DashboardPanel");

        UserDAO userDAO = new UserDAOImplementation();
        UserService userService = new UserService(userDAO);

        userController = new UserController(userService, loginPanel, registrationPanel);

        this.getContentPane().add(cards);
        showLoginPanel();
    }

    @Override
    public void onPanelChange(JPanel newPanel) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        if (newPanel instanceof LoginPanel) {
            cl.show(cards, "LoginPanel");
        } else if (newPanel instanceof RegistrationPanel) {
            cl.show(cards, "RegistrationPanel");
        } else if (newPanel instanceof DashboardPanel) {
            cl.show(cards, "DashboardPanel");
        }
    }

    private void showLoginPanel() {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "LoginPanel");
    }
}