package View.Dashboard;
import Controller.PasswordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private PasswordController passwordController;

    private JButton addNewButton;
    private JButton encryptionButton;


    public MenuPanel() {
        addNewButton = new JButton("Add New");
        encryptionButton = new JButton("Encryption");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(addNewButton);
        add(encryptionButton);

    }

    public JButton getAddNewButton() {
        return addNewButton;
    }
}
