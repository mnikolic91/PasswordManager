package View.Dashboard;

import Controller.PasswordController;
import Model.PasswordModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PasswordPanel extends JPanel {
    private JTable passwordTable;
    private DefaultTableModel passwordTableModel;
    private PasswordController passwordController;

    public PasswordPanel() {
        initializeTableModel();
        passwordController.refreshPasswordList();
    }

    private void initializeTableModel() {
        passwordTableModel = new DefaultTableModel();
        passwordTableModel.addColumn("Title");
        passwordTableModel.addColumn("Created");
        passwordTableModel.addColumn("Last Update");
        passwordTable = new JTable(passwordTableModel);
        add(new JScrollPane(passwordTable), BorderLayout.CENTER);
        passwordController.refreshPasswordList();
    }

    public void setPasswordController(PasswordController controller) {
        this.passwordController = controller;
    }

    public void updatePasswordTable(List<PasswordModel> passwords) {
        passwordController.refreshPasswordList();
        if (passwordTableModel != null) {
            passwordTableModel.setRowCount(0);
            for (PasswordModel password : passwords) {
                passwordTableModel.addRow(new Object[] {
                        password.getTitle(),
                        password.getCreationDate(),
                        password.getLastUpdateDate()
                });
            }
        }
    }
}
