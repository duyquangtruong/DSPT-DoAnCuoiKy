package main.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtil implements ConnectionUtils {

    @Override
    public Connection connectDB(String urlConfig) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Connection connectDB(String urlConfig,String username,String password) throws SQLException, ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "testjdbc";
        String userName = "root";
        String passWord = "root";
        String port = "8889";
        String url = "jdbc:mysql://" + hostName + ":"+port+"/" + dbName;
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
        System.out.println("Connect success");
        return con;
    }
}
