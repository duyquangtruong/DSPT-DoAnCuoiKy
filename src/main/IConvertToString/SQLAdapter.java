package main.IConvertToString;

import java.util.Map;

public class SQLAdapter extends DBAdapter {

    @Override
    public String deleteString(String tableName, String primaryKey, Map<String, String> fieldValues, int limit) {
        String delString = "DELETE TOP("+ String.valueOf(limit) +") FROM " + tableName;
        delString += " WHERE " + primaryKey + " = " + fieldValues.get(primaryKey);
        return delString;
    }
}
