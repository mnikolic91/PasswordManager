package Service;

import Model.DataAccessObjects.PasswordDAO;
import Model.DataAccessObjects.PasswordDAOImplementation;
import Model.PasswordModel;

import java.util.List;

public class PasswordService {

    private PasswordDAO passwordDAO;

    public PasswordService() {
        this.passwordDAO = new PasswordDAOImplementation();
    }

    public List<PasswordModel> getPasswordsForUser(int userID) {
        return passwordDAO.findByUserId(userID);
    }

    public void addNewPassword(PasswordModel newPassword) {
        passwordDAO.insert(newPassword);
    }

    public PasswordModel getPasswordById(int id) {
        return passwordDAO.findById(id);
    }

    public void updatePassword(PasswordModel updatedPassword) {
        passwordDAO.update(updatedPassword);
    }

    public void deletePassword(int passwordId, int userId) {
        passwordDAO.delete(passwordId, userId);
    }


}