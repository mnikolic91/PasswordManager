package Service;

import Model.DataAccessObjects.UserDAO;
import Model.UserModel;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean registerUser(String username, String password) {
        UserModel user = new UserModel(username, password, password);
        userDAO.insert(user);
        return true;
    }


    public boolean registerUser(String username, String hashedPassword, String salt) {
        UserModel user = new UserModel(username, hashedPassword, salt);
        userDAO.insert(user);
        return true;
    }


    public boolean loginUser(String username, String password) {

        UserModel user = userDAO.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }
}
