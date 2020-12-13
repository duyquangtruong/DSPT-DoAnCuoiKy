package main.jdbc;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleConnUtils implements ConnectionUtils {

    @Override
    public Connection connectDB(String urlConfig) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Connection connectDB(String urlConfig, String username, String password) throws SQLException, ClassNotFoundException {
        return null;
    }
}
