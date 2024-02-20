package Model.DataAccessObjects;

import Model.DatabaseConnection;
import Model.PasswordModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImplementation implements PasswordDAO {

    @Override
    public void insert(PasswordModel password) {
        String sql = "INSERT INTO passwords(userID, title, username, password, url, creationDate, lastUpdateDate) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, password.getUserID());
            pstmt.setString(2, password.getTitle());
            pstmt.setString(3, password.getUsername());
            pstmt.setString(4, password.getPassword());
            pstmt.setString(5, password.getUrl());
            pstmt.setString(6, password.getCreationDate());
            pstmt.setString(7, password.getLastUpdateDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<PasswordModel> findByUserId(int userId) {
        List<PasswordModel> passwords = new ArrayList<>();
        String sql = "SELECT * FROM passwords WHERE userID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PasswordModel password = new PasswordModel(
                        rs.getInt("userID"),
                        rs.getString("title"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("url"),
                        rs.getString("creationDate"),
                        rs.getString("lastUpdateDate")
                );
                passwords.add(password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return passwords;
    }

    @Override
    public void update(PasswordModel password) {
        String sql = "UPDATE passwords SET title = ?, username = ?, password = ?, url = ?, creationDate = ?, lastUpdateDate = ? WHERE entryID = ? AND userID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, password.getTitle());
            pstmt.setString(2, password.getUsername());
            pstmt.setString(3, password.getPassword());
            pstmt.setString(4, password.getUrl());
            pstmt.setString(5, password.getCreationDate());
            pstmt.setString(6, password.getLastUpdateDate());
            pstmt.setInt(7, PasswordModel.getEntryID());
            pstmt.setInt(8, password.getUserID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(PasswordModel password) {
        String sql = "DELETE FROM passwords WHERE entryID = ? AND userID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, PasswordModel.getEntryID());
            pstmt.setInt(2, password.getUserID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
