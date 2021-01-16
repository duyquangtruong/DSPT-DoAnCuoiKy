package main.IConvertToString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

public abstract class DBAdapter extends DBBaseQuery implements IConvertToString {

    @Override
    public String queryString(String select, String from, String where, String order, String groupBy, String having) {
        return querySQL(select, from, where, order, groupBy, having);
    }

    @Override
    public String queryString(String select, String from, Map<String, Object> params) {
        String where = "WHERE ";
        for (Map.Entry< String, Object> entry:
                params.entrySet()) {
            where+=entry.getKey() + " = '"+entry.getValue()+"' AND ";
        }
        if (where.equals("WHERE ")){
            where = " AND ";
        }
        return querySQL(select,from,where.substring(0,where.length()-5),"","","");
    }

    @Override
    public String field(String tableName, String fieldName) {
        return String.format("%s.%s",tableName,fieldName);
    }

    @Override
    public String parameter(String parameterId) {
        return "@" + parameterId;
    }


    @Override
    public String table(String tableName) {
        return String.format("%s",tableName);
    }

    @Override
    public String updateString(String tableName, String primaryKey, Map<String, String> fieldValues) {
        String upString = "update " + tableName + " SET ";
        for (Map.Entry<String, String> fieldValue : fieldValues.entrySet()) {
            upString += (fieldValue.getKey() + " = " + fieldValue.getValue() + ",");
        }
        upString = upString.substring(0, upString.length() - 1);
        upString += " WHERE " + primaryKey + " = " + fieldValues.get(primaryKey);
        return upString;
    }

    @Override
    public String insertString(String tableName, Map<String, String> fieldValues) {
        String insString = "insert into " + tableName;
        String fields = "";
        String values = "";

        for (Map.Entry<String, String> fieldValue : fieldValues.entrySet()) {
            fields += (fieldValue.getKey() + ",");
            values += (fieldValue.getValue() + ",");
        }

        fields = fields.substring(0, fields.length() - 1);
        values = values.substring(0, values.length() - 1);

        insString += ("(" + fields + ") values (" + values + ")");

        return insString;
    }

    @Override
    public String deleteString(String tableName, String primaryKey, Map<String, String> fieldValues) {
        String delString = "DELETE FROM " + tableName;
        delString += " WHERE " + primaryKey + " = " + fieldValues.get(primaryKey);
        return delString;
    }

    @Override
    abstract public String deleteString(String tableName, String primaryKey, Map<String, String> fieldValues, int limit);

    public PreparedStatement preparedStatementQuery(Connection conn, String select, String from, String where, String order
            , String groupBy, String having){

        return preparedStatementQuery(conn,select,from,where,order,groupBy,having);
    }
}
