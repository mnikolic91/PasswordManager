package Model.DataAccessObjects;
import Model.UserModel;

public interface UserDAO {
    void insert(UserModel user);
    UserModel findByUsername(String username);
    void update(UserModel user);
    void delete(UserModel user);

}
