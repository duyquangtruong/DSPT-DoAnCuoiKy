package main.IConvertToString;

import java.util.Map;


// ISqlAdapter:
public interface ConvertToString {

    String queryString(String select,String where,
                       String from,String order,
                       String groupBy,String having);
    String queryString(String select, String from, Map<String,Object> params);
    String field(String tableName, String fieldName);
    String table(String tableName);
    String parameter(String parameterId);

    String updateString(String tableName, String primaryKey, Map<String,String> fieldValues);
    String insertString(String tableName, Map<String, String> fieldValues);
    String deleteString(String tableName, String primaryKey, Map<String, String> fieldValues);
    String deleteString(String tableName, String primaryKey, Map<String, String> fieldValues,int limit);
}
