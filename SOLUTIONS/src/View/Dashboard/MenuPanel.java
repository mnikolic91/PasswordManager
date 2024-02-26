package View.Dashboard;

import View.Dashboard.EncryptionStrategy.AESEncryptionStrategy;
import View.Dashboard.EncryptionStrategy.EncryptionStrategy;
import View.Dashboard.EncryptionStrategy.RSAEncryptionStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MenuPanel extends JPanel {
    private JButton addNewButton;
    private JButton encryptionButton;
    private JPopupMenu encryptionMenu;
    private EncryptionStrategy currentEncryptionStrategy;
    private JButton refreshButton; // Dodajemo gumb za osvježavanje

    public MenuPanel() {
        addNewButton = new JButton("Add New");
        refreshButton = new JButton("Refresh"); // Inicijalizacija gumba za osvježavanje
        initializeEncryptionMenu();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(addNewButton);
        add(refreshButton); // Dodavanje gumba za osvježavanje na panel
        add(encryptionButton);
    }

    private void initializeEncryptionMenu() {
        encryptionMenu = new JPopupMenu();
        encryptionButton = new JButton("Encryption");

        JMenuItem aesEncryption = new JMenuItem("AES Encryption");
        aesEncryption.addActionListener(e -> {
            setCurrentEncryptionStrategy(new AESEncryptionStrategy());
        });

        JMenuItem rsaEncryption = new JMenuItem("RSA Encryption");
        rsaEncryption.addActionListener(e -> {
            setCurrentEncryptionStrategy(new RSAEncryptionStrategy());
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
