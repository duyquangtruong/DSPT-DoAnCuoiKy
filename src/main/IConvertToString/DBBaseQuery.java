package main.IConvertToString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// SQl adapter base:
abstract public class DBBaseQuery {
    public String querySQL(String select, String from,String where, String order
            , String groupBy, String having){
        if (where.isEmpty() || where.equals("")){
            return String.format("SELECT %s FROM %s %s %s %s",select,from,order,groupBy,having);
        }
        else if (where.contains("WHERE")){
            return String.format("SELECT %s FROM %s %s %s %s %s",select,from,where,order,groupBy,having);
        }
        else {
            return String.format("SELECT %s FROM %s WHERE %s %s %s %s",select,from,where,order,groupBy,having);
        }
    }

    public PreparedStatement preparedStatementQuery(Connection conn, String select, String from, String where, String order
            , String groupBy, String having){
        String sqlQuery = "SELECT ? FROM ? ? ? ? ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,select);
            stmt.setString(2,from);
            stmt.setString(3,where);
            stmt.setString(4,order);
            stmt.setString(5,groupBy);
            stmt.setString(6,having);
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }
}
