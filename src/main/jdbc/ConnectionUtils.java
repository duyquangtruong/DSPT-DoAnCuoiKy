package main.jdbc;

import ReadXML.UtilDB;
import ReadXML.UtilDBTarget;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConnectionUtils {
    Connection dbConnection = null;
    PreparedStatement ps = null;
    Statement statement = null;
    ResultSet rs = null;

    public void open(String connectionConfig) {
        System.out.println("Nooooo");
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

    public void open(UtilDBTarget dbTarget) {
        if (dbTarget.getUtil().getUsername().isEmpty() ||dbTarget.getUtil().getUsername().equals("") ){
            open(dbTarget.getUtil().getUrl());
        }
        else open(dbTarget.getUtil().getUrl(),dbTarget.getUtil().getUsername(),dbTarget.getUtil().getPassword());
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

    public Boolean executeQuery(String sqlQuery) {
        try {
            rs = statement.executeQuery(sqlQuery);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void executePreparedStatement(PreparedStatement pst){
        try {
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Empty implementation:
    public void excuteQuery(String sqlQuery, HashMap<Object, String> param){

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
