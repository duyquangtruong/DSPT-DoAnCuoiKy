package Generator;

import Generator.DBMapper.DBMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static Generator.DBMapper.DBMapper.FOREIGN_INFO_KEY_TABLE;

public class Writer {

    public static final String REQUIRED_YES = "YES";
    public static final String REQUIRED_NO = "NO";

    public static void writeClass(String className, DBMapper mapper){
        try {
            FileWriter classFile =  new FileWriter(className+".java");
            DatabaseMetaData databaseMetaData = mapper.getDatabaseMetaData();
            List<String> primaryKeys = mapper.getPrimaryKey(className);
            List<String> foreignKeys = mapper.getForeignKey(className);
            HashMap<String,String> attributes = mapper.getTableAttributes(className);

            classFile.write("@TableDB(\""+className+"\")\n");
            classFile.write("public class "+className+ " {\n");

            // Tao cac thuoc tinh cho class
            for (String attributeName:attributes.keySet()) {

                ResultSet resultSet = databaseMetaData.getAttributes(null,className,null,attributeName);
                if(resultSet== null) {
                    return;
                }

                classFile.write("@ColumnDB(\""+attributeName+"\")\n");

                if(primaryKeys.contains(attributeName)){
                    classFile.write("@PrimaryKey()\n");
                }
                if(foreignKeys.contains(attributeName)){
                    HashMap<String,String> foreignInfo = mapper.getForeignInfo(className,attributeName);
                    classFile.write("@ForeignKey(\""+foreignInfo.get(FOREIGN_INFO_KEY_TABLE)+"\")\n");
                }
                String isRequired = databaseMetaData.getAttributes(null,className,null,attributeName).getString("IS_NULLABLE");
                if(isRequired.equals(REQUIRED_YES)){
                    classFile.write("@Required()");
                }

                classFile.write("private "+attributes.get(attributeName)+" "+attributeName+";\n");
            }

            // Them create options ben duoi



            classFile.write("}");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
