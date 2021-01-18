package Generator;

import Generator.DBMapper.DBMapper;
import Generator.DBMapper.Standardizer;
import TableT.DataTypeMapper.DataTypeMapper;
import main.jdbc.Session;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static Generator.DBMapper.DBMapper.FOREIGN_INFO_KEY_TABLE;

public class Writer {

    public static final String REQUIRED_YES = "NO";
    public static final String srcOutput = System.getProperty("user.dir")+"\\src\\DAO";

    public static void writeClass(String className, DBMapper mapper){
        try {
            File directory = new File(srcOutput);
            if (!directory.exists()){
                directory.mkdir();
            }
            File myObj = new File(srcOutput+"\\"+className+".java");
            if (!myObj.exists()){
                myObj.createNewFile();
            }
            FileWriter out =  new FileWriter(myObj);
            BufferedWriter classFile = new BufferedWriter(out);
            DatabaseMetaData databaseMetaData = mapper.getDatabaseMetaData();
            List<String> primaryKeys = mapper.getPrimaryKey(className);
            List<String> foreignKeys = mapper.getForeignKey(className);
            HashMap<String,String> attributes = mapper.getTableAttributes(className);
            DataTypeMapper dataTypeMapper = Session.getSession().getSessionFactory().getDbFactory().getDBTypeMapper();

            classFile.write("package DAO;\n\n");
            classFile.write("import TableT.Annotation.Column.*;\n");
            classFile.write("import TableT.Annotation.*;\n\n");

            classFile.write("@TableDB(\""+className+"\")\n");
            classFile.write("public class "+className+ " {\n");

            // Tao cac thuoc tinh cho class
            for (String attributeName:attributes.keySet()) {

                classFile.write("\t@ColumnDB(\""+attributeName+"\")\n");

                if(primaryKeys.contains(attributeName)){
                    classFile.write("\t@PrimaryKey()\n");
                }
                if(foreignKeys.contains(attributeName)){
                    HashMap<String,String> foreignInfo = mapper.getForeignInfo(className,attributeName);
                    classFile.write("\t@ForeignKey(\""+foreignInfo.get(FOREIGN_INFO_KEY_TABLE)+"\")\n");
                }

                ResultSet requiredSet =databaseMetaData.getColumns(null,null,className,attributeName);
                requiredSet.first();
                String isRequired = requiredSet.getString("IS_NULLABLE");
                if(isRequired.equals(REQUIRED_YES)){
                    classFile.write("\t@Required()\n");
                }

                classFile.write("\tprivate "+dataTypeMapper.getClassType(attributes.get(attributeName)) +" "+ Standardizer.NameStandardize(attributeName)+";\n\n");
            }

            // Them getter setter cho cac thuoc tinh
            for (String attributeName:attributes.keySet()){
                String standardName = Standardizer.NameStandardize(attributeName);
                String firstLetterCapitalize = standardName.substring(0, 1).toUpperCase() + standardName.substring(1);
                String attrType = dataTypeMapper.getClassType(attributes.get(attributeName));

                classFile.write(String.format("\tpublic %s get%s() { return this.%s; }\n\n"
                        ,attrType,firstLetterCapitalize, standardName));

                classFile.write(String.format("\tpublic void set%s(%s value) { this.%s = value; }\n\n"
                        ,firstLetterCapitalize,attrType, standardName));
            }

            classFile.write("}");
            classFile.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


}
