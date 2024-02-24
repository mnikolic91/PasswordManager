package View.Dashboard;

import Controller.PasswordController;
import View.PanelChangeListener;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private PanelChangeListener panelChangeListener;
    private PasswordPanel passwordPanel;
    private MenuPanel menuPanel;
    private PreviewPanel previewPanel;
    private PasswordController passwordController;

    public DashboardPanel(PanelChangeListener panelChangeListener) {
        this.panelChangeListener = panelChangeListener;
        passwordPanel = new PasswordPanel();
        passwordController = new PasswordController(passwordPanel);

        setLayout(new BorderLayout());
        menuPanel = new MenuPanel();
        previewPanel = new PreviewPanel();
        add(menuPanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);
        add(previewPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
