package TableT.DataTypeMapper;

import java.util.HashMap;

public class MySQLDataTypeMapper extends DataTypeMapper{
    public MySQLDataTypeMapper() {
        this.classToDB = new HashMap<>();
        this.dBtoClass = new HashMap<>();
        classToDB.put("string","VARCHAR");
        classToDB.put("int","INTEGER");
        classToDB.put("float","FLOAT");
        classToDB.put("Boolean","BOOLEAN");

        dBtoClass.put("BIT","Boolean");
        dBtoClass.put("BOOL","Boolean");
        dBtoClass.put("BOOLEAN","Boolean");
        dBtoClass.put("INT","int");
        dBtoClass.put("INTEGER","int");
        dBtoClass.put("FLOAT","float");
        dBtoClass.put("VARCHAR","String");
        dBtoClass.put("TEXT","String");

    }

}
