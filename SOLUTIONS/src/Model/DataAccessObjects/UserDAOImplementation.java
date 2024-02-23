package Model.DataAccessObjects;

import Model.DatabaseConnection;
import Model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImplementation implements UserDAO {

    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO users(username, password, salt) VALUES(?,?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSalt());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        UserModel user = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new UserModel(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("salt"));
                user.setUserID(rs.getInt("userID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }


    @Override
    public void update(UserModel user) {
        String sql = "UPDATE users SET password = ?, salt = ? WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getSalt());
            pstmt.setString(3, user.getUsername());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void delete(UserModel user) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
