package main.IConvertToString;

import java.util.Map;

public class MySqlAdapter extends DBAdapter{


    @Override
    public String parameter(String parameterId) {
        return parameterId;
    }

    @Override
    public String deleteString(String tableName, String primaryKey, Map<String, String> fieldValues, int limit) {
        String delString = "delete from " + tableName;
        delString += " WHERE " + primaryKey + " = " + fieldValues.get(primaryKey);
        delString += " LIMIT "+ String.valueOf(limit);
        return delString;
    }
}
