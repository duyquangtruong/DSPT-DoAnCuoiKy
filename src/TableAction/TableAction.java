package TableAction;

import TableT.Table.Column;
import main.IConvertToString.IConvertToString;
import main.jdbc.ConnectionUtils;
import main.jdbc.Session;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class TableAction {
    protected Map<String, Column> columns;
    protected ConnectionUtils cnn;
    protected String tableName;
    protected String primaryKey;
    public IConvertToString getIConvertToString()
    {
        return Session.getSession().getConvertToString();
    }

    //GetFieldValue
    protected abstract String getSQL(Map<String,String> fields);
    private Map<String, String> getField(Object obj){
        if (obj==null){
            return null;
        }
        Map<String,String> fields = new HashMap<>();
        for (Map.Entry<String,Column> column:columns.entrySet()){
            try {
                Field field = obj.getClass().getDeclaredField(column.getKey());
                Object value = field.get(obj);
                if (value==null && column.getValue().getRequired()==true){
                    return null;
                }
                String name = column.getValue().getColumnName();
                String vl=value.toString();
                if(column.getValue().getColumnType().equals("text")){
                    vl = "\'"+vl+"\'";
                }
                fields.put(name,vl);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return fields;

    }

    public TableAction(Map<String, Column> columns, ConnectionUtils cnn, String tableName, String primaryKey) {
        this.columns = columns;
        this.cnn = cnn;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    public Boolean execute(Object obj){
        if (obj == null){
            return false;
        }
        Map<String,String> fields = getField(obj);
        if (fields==null){
            return false;
        }
        String sql = getSQL(fields);
        return cnn.executeQuery(sql);
    }


}
