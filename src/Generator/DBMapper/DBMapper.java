package Generator.DBMapper;

import java.util.Dictionary;
import java.util.List;

public abstract class DBMapper {
    protected DBDriver dbDriver;

    public void setDriver(DBDriver driver)
    {
        this.dbDriver = driver;
    }


    public abstract List<String> getAllTables();
    public abstract String getPrimaryKey(String tableName);
    public abstract String getForeignKey(String tableName);
    public abstract Dictionary<String,String> getTableAttributes(String tableName);
}
