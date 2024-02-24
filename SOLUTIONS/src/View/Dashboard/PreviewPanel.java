package View.Dashboard;

import javax.swing.*;
import java.awt.*;

public class PreviewPanel extends JPanel {
    private JButton updatePasswordButton;
    private JButton deletePasswordButton;
    private JButton generatePasswordButton;

    public PreviewPanel() {
        updatePasswordButton = new JButton("Update Password");
        deletePasswordButton = new JButton("Delete Password");
        generatePasswordButton = new JButton("Generate a Strong Password");
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(updatePasswordButton);
        add(deletePasswordButton);
        add(generatePasswordButton);
    }
}