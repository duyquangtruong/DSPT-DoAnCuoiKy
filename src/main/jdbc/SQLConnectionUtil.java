package main.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionUtil implements ConnectionUtils {
    @Override
    public Connection connectDB(String urlConfig) throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlserver://DESKTOP-M6GLUI6\\MSSQLSERVER1;databaseName=QLBH;integratedSecurity=true";
        Connection con = null;
        try {
            //registering the jdbc driver here, your string to use
            //here depends on what driver you are using.
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(urlConfig);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Cấu trúc URL Connection dành cho Oracle
        // Ví dụ: jdbc:mysql://localhost:3306/simplehr

        return con;
    }

    @Override
    public Connection connectDB(String urlConfig, String username, String password) throws SQLException, ClassNotFoundException {
        /* String connectionURL = "jdbc:sqlserver://" + hostName + ":1433/"
                + dbName + ";instance=" + sqlInstanceName; */
        String url = "jdbc:sqlserver://DESKTOP-M6GLUI6\\MSSQLSERVER1;databaseName=QLBH;integratedSecurity=true";
        Connection con = null;
        try {
            //registering the jdbc driver here, your string to use
            //here depends on what driver you are using.
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(urlConfig,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Cấu trúc URL Connection dành cho Oracle
        // Ví dụ: jdbc:mysql://localhost:3306/simplehr

        return con;
    }

}
