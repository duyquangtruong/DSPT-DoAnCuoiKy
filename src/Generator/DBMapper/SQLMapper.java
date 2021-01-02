package Generator.DBMapper;

import java.util.Dictionary;
import java.util.List;

public class SQLMapper extends DBMapper {
    @Override
    public List<String> getAllTables() {
        return null;
    }

    @Override
    public String getPrimaryKey(String tableName) {
        return null;
    }

    @Override
    public String getForeignKey(String tableName) {
        return null;
    }

    @Override
    public Dictionary<String, String> getTableAttributes(String tableName) {
        return null;
    }
}
