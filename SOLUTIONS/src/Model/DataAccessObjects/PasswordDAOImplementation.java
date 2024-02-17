package Model.DataAccessObjects;

import Model.PasswordModel;

import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImplementation implements PasswordDAO{

    @Override
    public void insert(PasswordModel password) {
    }

    @Override
    public List<PasswordModel> findByUserId(int userId) {
        List<PasswordModel> passwords = new ArrayList<>();

        return passwords;
    }

    @Override
    public void update(PasswordModel password) {
    }

    @Override
    public void delete(PasswordModel password) {
    }
}
