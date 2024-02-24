package View.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton addNewButton;
    private JButton encryptionButton;

    public MenuPanel() {
        addNewButton = new JButton("Add New");
        encryptionButton = new JButton("Encryption");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(addNewButton);
        add(encryptionButton);
    }

    ActionListener addNewListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
}