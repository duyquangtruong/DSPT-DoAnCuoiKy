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

    @Override
    protected String GetPrimaryKeySQL(String tableName) {
        return null;
    }

    @Override
    protected String GetTableForeignKeysSQL(String tableName) {
        return "SELECT k.COLUMN_NAME,  k.REFERENCED_TABLE_NAME FROM information_schema.TABLE_CONSTRAINTS i LEFT JOIN information_schema.KEY_COLUMN_USAGE k ON i.CONSTRAINT_NAME = k.CONSTRAINT_NAME  WHERE i.CONSTRAINT_TYPE = 'FOREIGN KEY'  AND i.TABLE_SCHEMA = DATABASE() AND i.TABLE_NAME = '" + tableName + "'";
    }

}
