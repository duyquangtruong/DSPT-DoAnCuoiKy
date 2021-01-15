package TableT.DataTypeMapper;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class DataTypeMapper {
    protected Map<String,String> classToDB;
    protected Map<String,String> dBtoClass;

    public DataTypeMapper() {
        this.classToDB = new HashMap<>();
        this.dBtoClass = new HashMap<>();
    }

     public String getDBType(String t){
        if (classToDB.containsKey(t)){
            return classToDB.get(t);
        }

        String[] arrOfStr = t.split("\\.");
        if (arrOfStr.length>0){
            return classToDB.get(arrOfStr[arrOfStr.length-1].toLowerCase());
        }

        return null;
    }
    public String getClassType(String t){
        if (dBtoClass.containsKey(t)){
            return dBtoClass.get(t);
        }

        return "Object";
    }
    protected abstract String GetPrimaryKeySQL(String tableName);

    protected abstract String GetTableForeignKeysSQL(String tableName);

}
