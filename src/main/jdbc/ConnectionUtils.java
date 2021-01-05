package main.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {
    Connection dbConnection = null;
    PreparedStatement ps = null;
    Statement statement = null;
    ResultSet rs = null;

    public void open(String connectionConfig) {
        try {
            //registering the jdbc driver here, your string to use
            //here depends on what driver you are using.
            //Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionConfig);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void open(String connectionConfig, String username, String password) {
        try {
            //registering the jdbc driver here, your string to use
            //here depends on what driver you are using.
            //Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionConfig,username,password);
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (dbConnection!=null){
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void executeQuery(String sqlQuery) {
        try {
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executePreparedStatement(PreparedStatement pst){
        try {
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getAllRows() {
        try {
            List<Object[]> rows = new ArrayList<>();
            while (rs.next()) {
                List<Object> rowData = new ArrayList<>();
                for (int columnIndex = 1; columnIndex <= rs.getMetaData().getColumnCount(); columnIndex++) {
                    rowData.add(rs.getObject(columnIndex));
                }
                rows.add(rowData.toArray());
            }
            return rows;

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public Connection getDbConnection(){
        return dbConnection;
    }
}
