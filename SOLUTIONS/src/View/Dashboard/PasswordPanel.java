package View.Dashboard;

import Model.PasswordModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PasswordPanel extends JPanel {
    private JTable passwordTable;
    private DefaultTableModel passwordTableModel;

    public PasswordPanel() {
        initializeTableModel();
    }

    private void initializeTableModel() {
        passwordTableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Created", "Last Update"}, 0);
        passwordTable = new JTable(passwordTableModel);
        add(new JScrollPane(passwordTable), BorderLayout.CENTER);
    }

    public void updatePasswordTable(List<PasswordModel> passwords) {
        passwordTableModel.setRowCount(0);
        for (PasswordModel password : passwords) {
            passwordTableModel.addRow(new Object[]{
                    password.getEntryID(),
                    password.getTitle(),
                    password.getCreationDate(),
                    password.getLastUpdateDate()
            });
        }
    }

    public void addPasswordSelectionListener(ListSelectionListener listener) {
        passwordTable.getSelectionModel().addListSelectionListener(listener);
    }

    public JTable getPasswordTable() {
        return passwordTable;
    }

    public void setPasswordTable(JTable passwordTable) {
        this.passwordTable = passwordTable;
    }

    public DefaultTableModel getPasswordTableModel() {
        return passwordTableModel;
    }

    public void setPasswordTableModel(DefaultTableModel passwordTableModel) {
        this.passwordTableModel = passwordTableModel;
    }
}