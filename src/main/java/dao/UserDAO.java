package dao;

import DBUtil.MainDBConnection;
import java.sql.CallableStatement;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ModelException;

/**
 *
 * @author User
 */
public class UserDAO {

    private UserDAO() {
    }

    /**
     * NOTE: ResultSet must be closed after usage.
     *
     * @param email
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ResultSet getUser(String email, String password) throws ClassNotFoundException, SQLException {
        Connection connection = MainDBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        if (connection != null) {
            String query = "SELECT users.[Email Address], [Full Name], [Student ID], [Password], [Role]\n"
                    + "FROM Users users\n"
                    + "    INNER JOIN Roles roles ON users.[Email Address] = roles.[Email Address]\n"
                    + "WHERE users.[Email Address] = ? AND users.[Password] = ? COLLATE Latin1_General_CS_AI;";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            result = statement.executeQuery();
        }
        /**
         * NOTE: DO NOT CLOSE CONNECTION. I REPEAT: DO NOT CLOSE CONNECTION OR
         * RESULTSET WON'T BE ACCESSIBLE IN OTHER FILES.
         */
        return result;
    }

    public static boolean addUser(User current) throws SQLException, ClassNotFoundException {
        Connection connection = MainDBConnection.getConnection();
        CallableStatement statement;
        int rows = 0;
        if (connection != null) {
            String queryUsers = "INSERT INTO Users\n"
                    + "    ([Full Name],[Email Address],[Student ID],[Password])\n"
                    + "VALUES\n"
                    + "    (?, ?, ?, ?);";
            statement = connection.prepareCall(queryUsers);
            statement.setString(1, current.getFullName());
            statement.setString(2, current.getEmailAddress());
            statement.setString(3, current.getStudentId());
            statement.setString(4, current.getPassword());
            rows += statement.executeUpdate();
            statement.close();

            String queryRows = "INSERT INTO Roles\n"
                    + "    ([Email Address],Role)\n"
                    + "VALUES\n"
                    + "    (?, ?);";
            statement = connection.prepareCall(queryRows);
            statement.setString(1, current.getEmailAddress());
            statement.setString(2, current.getRole());
            rows += statement.executeUpdate();
            statement.close();
        }
        return rows == 2;
    }

    public static void main(String[] args) throws ModelException, SQLException, ClassNotFoundException {
        ResultSet result = getUser("danganhsang2003@gmail.com", "helloworld123$");
        if(result != null) {
            result.next();
            System.out.println(result.getString("Full Name"));
        } else {
            System.out.println("FAIL");
        }
    }
}
