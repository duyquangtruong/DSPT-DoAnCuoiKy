package TableAction;

import TableT.Table.Column;
import main.jdbc.ConnectionUtils;

import java.util.Map;

public class InsertAction extends TableAction {

    public InsertAction(Map<String, Column> columns,  String tableName, String primaryKey) {
        super(columns, tableName, primaryKey);
    }
}
