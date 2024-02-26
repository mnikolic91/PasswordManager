package Service;

import Model.DataAccessObjects.UserDAO;
import Model.PasswordUtil;
import Model.UserModel;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean loginUser(String username, String password) {
        UserModel user = userDAO.findByUsername(username);
        if (user != null) {
            String hashedPassword = PasswordUtil.hashPassword(password, user.getSalt());
            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }


    public boolean registerUser(String username, String hashedPassword, String salt) {
        UserModel user = new UserModel(username, hashedPassword, salt);
        userDAO.insert(user);
        return true;
    }

    public UserModel findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

}