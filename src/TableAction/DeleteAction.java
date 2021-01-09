package TableAction;

import TableT.Table.Column;
import main.jdbc.ConnectionUtils;

import java.util.Map;

public class DeleteAction extends TableAction {

    public DeleteAction(Map<String, Column> columns, ConnectionUtils cnn, String tableName, String primaryKey) {
        super(columns, cnn, tableName, primaryKey);
    }

    @Override
    protected String getSQL(Map<String, String> fields) {
        return getIConvertToString().deleteString(tableName,primaryKey,fields);
    }
}
