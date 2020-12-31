package Table;

import Annotation.Column.ForeignKey;
import Annotation.Column.ColumnDB;
import Annotation.Column.PrimaryKey;
import Annotation.Column.Required;
import Annotation.HasOne;
import Annotation.HasMany;
import Annotation.TableDB;
import DataTypeMapper.DataTypeMapper;
import DataTypeMapper.SQLDataTypeMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Table<T> {
    private T tobject;

    public Table(Class<T> tclass)
            throws InstantiationException, IllegalAccessException {

//        this.tobject = (T) tclass.newInstance();
        this.tobject = (T) tclass.newInstance();
        System.out.println(tclass.getClass());
    }

    public T getTObject() {
        return this.tobject;
    }

    private String tableName;
    private String primaryKeyDB="";
    //UnitOfWork
    Map<String, Column> columns ;
    Map<String, String> hasOne ;
    Map<String, String> hasMany ;

//    private SQLDriver.ISQLDriver _sqlDriver() {
//                   return Session.getCurSession().SQLDriver;
//    }

    //private SQLBuilder.SQLOffsetLimit offsetLimit = new SQLBuilder.SQLOffsetLimit();
    public Table(String name)
    {
        tableName = name;
        mapTable();
    }
    public Table()
    {
        mapTable();
    }

    public String getTableName(Field field) {
        TableDB tableName = field.getDeclaredAnnotation(TableDB.class);
        if(tableName!=null ){
            return tableName.value();

        }
        else {
            String[] arrOfStr = tableName.value().split(".", 0);
            if (arrOfStr.length>0){
                return arrOfStr[arrOfStr.length-1];
            }

        }
        return null;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKey() {
        for (Map.Entry<String,Column> column:columns.entrySet()){
//            System.out.println(column.getValue().getPrimaryKey());
            if(column.getValue().getPrimaryKey()){

              return column.getValue().getColumnName();
            }
        }
        return null;
    }

    public String getRefColumnName(String tableName) {
        tableName=tableName.toLowerCase();
        for (Map.Entry<String,Column> column:columns.entrySet()){
            if(column.getValue().getPrimaryKey()){
                if(column.getValue().getRefTable()==tableName){
                    return column.getValue().getColumnName();
                }
            }
        }
        return null;
    }

    public String getColumnName(String attrName) {
        return columns.get(attrName).getColumnName();
    }

    private String getDataSQL(){
        String data="";
        for (Map.Entry<String,Column> column:columns.entrySet()){
                data+=column.getValue().getColumnName() + ", ";
            }
        return data.substring(0,data.length()-1);
    }

    private void mapTable() {
        Class<?> cls = HocSinh.class;
//        HocSinh hs = cls.getDeclaredAnnotation(HocSinh.class)
        // this.tableName
        columns = new HashMap<String, Column>();
        hasOne = new HashMap<>();
        hasMany = new HashMap<>();
        DataTypeMapper dataTypeMapper = new SQLDataTypeMapper();
        TableDB tableNameDB = cls.getAnnotation(TableDB.class);
        if(tableNameDB!=null ){
            tableName= tableNameDB.value();
        }
        else {
            String[] arrOfStr = tableNameDB.value().split("\\.");
            if (arrOfStr.length>0){
                tableName=  arrOfStr[arrOfStr.length-1];
            }
        }
        Field fieldlist[] = cls.getDeclaredFields();
        for (Field field : fieldlist) {
            // build toString output with StringBuilder()
            field.setAccessible(true);
//            System.out.println(field);




            HasOne ho = field.getDeclaredAnnotation(HasOne.class);
            if(ho!=null ){
                hasOne.put(field.getName(),ho.value());
                continue;

            }
//            System.out.println(field.getType());

            HasMany hm = field.getDeclaredAnnotation(HasMany.class);
            if(hm!=null ){
                hasOne.put(field.getName(),hm.value());
                continue;
            }

            String columnName = field.getName();
            String columnType = dataTypeMapper.getDBType(field.getType().toString());
            boolean isPrimaryKey = false;
            boolean isRequired=false;
            ColumnDB columnDB = field.getDeclaredAnnotation(ColumnDB.class);
            if(columnDB !=null ){
               columnName= columnDB.value();
            }
            PrimaryKey primaryKey = field.getDeclaredAnnotation(PrimaryKey.class);

            if(primaryKey!=null ){
                if (primaryKey.value().equals("true")){
                    isPrimaryKey=true;
                }

            }
            Required required = field.getDeclaredAnnotation(Required.class);
            if(required!=null ){
                if (required.value()=="true"){
                    isRequired=true;
                }
                else {
                    isRequired=false;
                }
            }

            ForeignKey foreignKey = field.getDeclaredAnnotation(ForeignKey.class);
            if(foreignKey!=null ){
                columns.put(field.getName(),new Column(columnName,columnType,foreignKey.value()));
            }
            else{
//                System.out.println("Column{" +
//                        "columnName='" + columnName + '\'' +
//                        ", columnType='" + columnType + '\'' +
//                        ", isPrimaryKey=" + isPrimaryKey +
//                        ", isForeignKey=" +
//                        ", isRequired=" + isRequired +
//                        ", refTable='"  + '\'' +
//                        '}');
                columns.put(field.getName(),new Column(columnName,columnType,isPrimaryKey,isRequired));
            }
            for (Map.Entry<String,Column> column:columns.entrySet()){
                if(column.getValue().getPrimaryKey()){
                    primaryKeyDB=column.getKey();
                }
            }
//                System.out.println(Optional.ofNullable(p).map(ForeignKey::value).orElse(field.getName()));


            //not map


        }

        for (Map.Entry<String,Column> column:columns.entrySet()){
            System.out.println(column.getValue().toString());
        }

    }

}