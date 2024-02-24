package View.Dashboard;

import Controller.PasswordController;
import View.PanelChangeListener;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private PanelChangeListener panelChangeListener;
    private PasswordController passwordController;
    private PasswordPanel passwordPanel;
    private MenuPanel menuPanel;
    private PreviewPanel previewPanel;

    public DashboardPanel(PanelChangeListener panelChangeListener) {
        this.panelChangeListener = panelChangeListener;
        passwordPanel = new PasswordPanel();

        passwordController = new PasswordController(passwordPanel);

        passwordPanel.setPasswordController(passwordController);

        setLayout(new BorderLayout());
        menuPanel = new MenuPanel();
        previewPanel = new PreviewPanel();
        add(menuPanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);
        add(previewPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
