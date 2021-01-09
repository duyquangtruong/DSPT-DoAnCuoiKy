package TableAction;

import TableT.Table.Column;
import main.jdbc.ConnectionUtils;

import java.util.Map;

public class UpdateAction extends TableAction {

    public UpdateAction(Map<String, Column> columns, ConnectionUtils cnn, String tableName, String primaryKey) {
        super(columns, cnn, tableName, primaryKey);
    }

    @Override
    protected String getSQL(Map<String, String> fields) {
        return getIConvertToString().updateString(tableName,primaryKey,fields);
    }
}
