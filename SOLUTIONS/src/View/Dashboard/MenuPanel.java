package View.Dashboard;

import View.Dashboard.EncryptionStrategy.CaesarCipherStrategy;
import View.Dashboard.EncryptionStrategy.EncryptionStrategy;
import View.Dashboard.EncryptionStrategy.XOREncryptionStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MenuPanel extends JPanel {
    private JButton addNewButton;
    private JButton encryptionButton;
    private JPopupMenu encryptionMenu;
    private EncryptionStrategy currentEncryptionStrategy;
    private JButton refreshButton;

    public MenuPanel() {
        addNewButton = new JButton("Add New");
        refreshButton = new JButton("Refresh");
        initializeEncryptionMenu();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(addNewButton);
        add(refreshButton);
        add(encryptionButton);
    }

    private void initializeEncryptionMenu() {
        encryptionMenu = new JPopupMenu();
        encryptionButton = new JButton("Encryption");

        JMenuItem aesEncryption = new JMenuItem("Caesar Encryption");
        aesEncryption.addActionListener(e -> {
            setCurrentEncryptionStrategy(new CaesarCipherStrategy());
        });

        JMenuItem rsaEncryption = new JMenuItem("XORE Encryption");
        rsaEncryption.addActionListener(e -> {
            setCurrentEncryptionStrategy(new XOREncryptionStrategy());
        });

        encryptionMenu.add(aesEncryption);
        encryptionMenu.add(rsaEncryption);

        encryptionButton.addActionListener(e -> encryptionMenu.show(encryptionButton, encryptionButton.getWidth()/2, encryptionButton.getHeight()/2));
    }

    public void setCurrentEncryptionStrategy(EncryptionStrategy strategy) {
        this.currentEncryptionStrategy = strategy;
        triggerEncryptionLogic();
    }

    private void triggerEncryptionLogic() {
        if (currentEncryptionStrategy != null) {
            String encryptedData = currentEncryptionStrategy.encrypt("Example Data");
            System.out.println(encryptedData);
        }
    }

    public void setRefreshAction(ActionListener actionListener) {
        refreshButton.addActionListener(actionListener);
    }

    public JButton getAddNewButton() {
        return addNewButton;
    }

    public EncryptionStrategy getCurrentEncryptionStrategy() {
        return currentEncryptionStrategy;
    }
}
