package Model.DataAccessObjects;

import Model.DatabaseConnection;
import Model.PasswordModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImplementation implements PasswordDAO {

    @Override
    public void insert(PasswordModel password) {
        String sql = "INSERT INTO password_entries(userID, title, username, password, url, creationDate, lastUpdateDate, encryptionType) VALUES(?,?,?,?,?,?,?,?)";

        password.setCreationDate(currentDate());
        password.setLastUpdateDate(currentDate());

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, password.getUserID());
            pstmt.setString(2, password.getTitle());
            pstmt.setString(3, password.getUsername());
            pstmt.setString(4, password.getPassword());
            pstmt.setString(5, password.getUrl());
            pstmt.setString(6, password.getCreationDate());
            pstmt.setString(7, password.getLastUpdateDate());
            pstmt.setString(8, password.getEncryptionType());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public List<PasswordModel> findByUserId(int userId) {
        List<PasswordModel> passwords = new ArrayList<>();
        String sql = "SELECT * FROM password_entries WHERE userID = ?";

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
                        rs.getString("url")
                );
                password.setEntryID(rs.getInt("entryID"));
                password.setCreationDate(rs.getString("creationDate"));
                password.setLastUpdateDate(rs.getString("lastUpdateDate"));
                password.setEncryptionType(rs.getString("encryptionType"));
                passwords.add(password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return passwords;
    }

    @Override
    public PasswordModel findById(int id) {
        String sql = "SELECT * FROM password_entries WHERE entryID = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                PasswordModel password = new PasswordModel(
                        rs.getInt("userID"),
                        rs.getString("title"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("url")
                );
                password.setEntryID(rs.getInt("entryID"));
                password.setEncryptionType(rs.getString("encryptionType"));
                return password;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void update(PasswordModel password) {
        String sql = "UPDATE password_entries SET title = ?, username = ?, password = ?, url = ?, lastUpdateDate = ?, encryptionType = ? WHERE entryID = ? AND userID = ?";

        password.setLastUpdateDate(currentDate());

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, password.getTitle());
            pstmt.setString(2, password.getUsername());
            pstmt.setString(3, password.getPassword());
            pstmt.setString(4, password.getUrl());
            pstmt.setString(5, password.getLastUpdateDate());
            pstmt.setString(6, password.getEncryptionType());
            pstmt.setInt(7, password.getEntryID());
            pstmt.setInt(8, password.getUserID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void delete(int entryID, int userID) {
        String sql = "DELETE FROM password_entries WHERE entryID = ? AND userID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entryID);
            pstmt.setInt(2, userID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    private String currentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }
}
