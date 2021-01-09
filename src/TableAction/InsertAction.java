package TableAction;

import TableT.Table.Column;
import main.jdbc.ConnectionUtils;

import java.util.Map;

public class InsertAction extends TableAction {

    public InsertAction(Map<String, Column> columns, ConnectionUtils cnn, String tableName, String primaryKey) {
        super(columns, cnn, tableName, primaryKey);
    }

    @Override
    protected String getSQL(Map<String, String> fields) {
        return getIConvertToString().insertString(tableName,fields);
    }
}
