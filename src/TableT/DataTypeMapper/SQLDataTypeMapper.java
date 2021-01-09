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

        dBtoClass.put("bigint","long");
        dBtoClass.put("binary","byte[]");
        dBtoClass.put("bit","boolean");
        dBtoClass.put("char","String");
        dBtoClass.put("date","Date");
        dBtoClass.put("datetime","Timestamp");
        dBtoClass.put("datetimeoffset","DateTimeOffset");
        dBtoClass.put("decimal","BigDecimal");
        dBtoClass.put("image","byte[]");
        dBtoClass.put("int","int");
        dBtoClass.put("money","BigDecimal");
        dBtoClass.put("nchar","String");
        dBtoClass.put("ntext","String");
        dBtoClass.put("numeric","BigDecimal");
        dBtoClass.put("nvarchar","String");
        dBtoClass.put("nvarchar(max)","String");
        dBtoClass.put("real","float");
        dBtoClass.put("smallint","short");

        dBtoClass.put("time","Time");
        dBtoClass.put("tinyint","short");
        dBtoClass.put("varbinary","byte[]");
        dBtoClass.put("varchar","String");
    }

}
