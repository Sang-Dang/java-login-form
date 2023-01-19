package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
class AzureDBConnection {
    private final static String ip = "localhost";
    private final static String instanceName = "javaloginserver.database.windows.net";
    private final static String port = "1433";
    private final static String user = "sangdang";
    private final static String password = "JavaLoginServer1";
    private final static String databaseName = "JavaLogin";
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
        return DriverManager.getConnection("jdbc:sqlserver://javaloginserver.database.windows.net:1433;database=JavaLogin;user=sangdang;password=JavaLoginServer1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
//        return DriverManager.getConnection(createURL());
    }
}
