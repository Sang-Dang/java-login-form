package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
class LocalDBConnection {
    private final static String ip = "localhost";
    private final static String instanceName = "SANG-DANG\\SQLEXPRESS";
    private final static String port = "1433";
    private final static String user = "sa";
    private final static String password = "12345";
    private final static String databaseName = "JavaLoginDatabase";
    private final static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    private static String createURL() {
        return "jdbc:sqlserver://" + ip + "\\" + instanceName + ":" + port + ";databasename=" + databaseName + ";user=" + user + ";password=" + password;
    }
    
    /**
     * Get a connection to the local database
     * 
     * @return a Connection if successful. If fails, returns null
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(createURL());
    }
}
