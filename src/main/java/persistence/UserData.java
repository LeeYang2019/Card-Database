package edu.matc.persistence;

import edu.matc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance(); //get instance of db
        Connection connection = null;
        String sql = "SELECT * FROM users;";

        try {
            database.connect(); //connect to db with login
            connection = database.getConnection(); //get the connection
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);

            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.error("SearchUser.getAllUsers()...Exception: " + e);
        } catch (Exception e) {
            logger.error("SearchUser.getAllUsers()...Exception: " + e);
        }
        return users;
    }

    public List<User> getUserByTerm(String searchTerm, String searchType) throws SQLException, ClassNotFoundException{

        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance();
        Connection connection = null;
        String searchItem = searchTerm + "%";
        String queryString = "";

        switch(searchType) {
            case "ID":
                queryString = "SELECT * FROM users WHERE id Like ?";
                break;
            case "firstName":
                queryString = "SELECT * FROM users WHERE first_name Like ?";
                break;
            case "lastName":
                queryString = "SELECT * FROM users WHERE last_name Like ?";
                break;
            default:
                break;
        }

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(queryString);
            pstmt.setString(1, searchItem);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                User employee = createUserFromResults(resultSet);
                System.out.println(employee.getFirstName());
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.error("SearchUser.getAllUsers()...Exception: " + e);
        } catch (Exception e) {
            logger.error("SearchUser.getAllUsers()...Exception: " + e);
        }

        return users;
    }

    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setLastName(results.getString("last_name"));
        user.setFirstName(results.getString("first_name"));
        user.setUserName(results.getString("user_name"));
        user.setId(results.getInt("id"));
        user.setDob(results.getString("date_of_birth"));
        return user;
    }

}