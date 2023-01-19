package DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class MainDBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return AzureDBConnection.getConnection();
    }
}
