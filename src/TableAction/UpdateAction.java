package TableAction;

import TableT.Table.Column;
import main.jdbc.ConnectionUtils;

import java.util.Map;

public class UpdateAction extends TableAction {

    public UpdateAction(Map<String, Column> columns, String tableName, String primaryKey) {
        super(columns, tableName, primaryKey);
    }

}
