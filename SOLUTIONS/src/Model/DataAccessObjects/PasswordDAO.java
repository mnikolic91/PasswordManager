package Model.DataAccessObjects;

import Model.PasswordModel;

import java.util.List;

public interface PasswordDAO {

    void insert(PasswordModel password);
    List<PasswordModel> findByUserId(int userId);
    void update(PasswordModel password);
    void delete(int entryID, int userID);
    PasswordModel findById(int id);

}
