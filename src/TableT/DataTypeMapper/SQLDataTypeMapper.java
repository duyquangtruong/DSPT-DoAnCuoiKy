package TableT.DataTypeMapper;

import java.util.HashMap;
import java.util.Map;

public class SQLDataTypeMapper extends DataTypeMapper{
    public SQLDataTypeMapper() {
        this.classToDB = new HashMap<>();
        this.dBtoClass = new HashMap<>();
        classToDB.put("string","text");
        classToDB.put("int","integer");
        classToDB.put("float","float");

        dBtoClass.put("text","String");
        dBtoClass.put("integer","int");
        dBtoClass.put("float","float");
        dBtoClass.put("int(11)","int");
    }

}
