package main.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

interface ConnectionUtils {
    Connection connectDB(String urlConfig) throws SQLException,ClassNotFoundException;
    Connection connectDB(String urlConfig,String username,String password) throws SQLException,ClassNotFoundException;
}
