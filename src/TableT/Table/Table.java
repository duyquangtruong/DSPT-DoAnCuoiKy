package TableT.Table;

import Generator.DBContext.DBContext;
import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import TableAction.DeleteAction;
import TableAction.InsertAction;
import TableAction.TableAction;
import TableAction.UpdateAction;
import TableT.Annotation.Column.ColumnDB;
import TableT.Annotation.Column.ForeignKey;
import TableT.Annotation.Column.PrimaryKey;
import TableT.Annotation.Column.Required;
import TableT.Annotation.HasMany;
import TableT.Annotation.HasOne;
import TableT.Annotation.TableDB;
import TableT.DataTypeMapper.DataTypeMapper;

import main.IConvertToString.IConvertToString;
import main.jdbc.ConnectionUtils;
import main.jdbc.Session;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class Table<T> {
    private Class<T> tobject;
    private IConvertToString converter;
    private ConnectionUtils connector;

    UpdateAction updateAction = null;
    InsertAction insertAction = null;
    DeleteAction deleteAction = null;
    private Map<String, Field> fields = new HashMap<>();

    private String tableName;
    private String primaryKeyDB="";
    //DB Context
    DBContext dbContext = new DBContext<T>();

    Map<String, Column> columns ;
    Map<String, String> hasOne ;
    Map<String, String> hasMany ;

    public Table(Class<T> tclass) {

        tobject = tclass;

        converter = Session.getSession().getConvertToString();
        connector = Session.getSession().getConn();
        List<Field> fieldList = Arrays.asList(tclass.getDeclaredFields());
        for (Field field : fieldList) {
            ColumnDB col = field.getAnnotation(ColumnDB.class);
            if (col != null) {
                field.setAccessible(true);
                fields.put(col.value(), field);
            }
        }
        mapTable();
    }

    public Class getTObject() {
        return tobject;
    }
    public ConnectionUtils getConnector(){return connector;}

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
        if(columns!=null){
            for (Map.Entry<String,Column> column:columns.entrySet()){
                if(column.getValue().getPrimaryKey()==true){
                    return column.getValue().getColumnName();
                }
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
        Class<?> cls = tobject;
//        HocSinh hs = cls.getDeclaredAnnotation(HocSinh.class)
        // this.tableName
        columns = new HashMap<String, Column>();
        hasOne = new HashMap<>();
        hasMany = new HashMap<>();
        DataTypeMapper dataTypeMapper = Session.getSession().getSessionFactory().getDbFactory().getDBTypeMapper();
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
            if(columnDB != null ){
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

    }

    public T load(Object id){
        T dto = GetInstance(id);
        if (dto != null){
            return dto;
        }

        Map<String,Object> params = new HashMap<String,Object>();
        params.put(getPrimaryKey(),id);
        String queryString = converter.queryString("*",getTableName(),params);
        try {
            dto = map(connector.excuteQueryRow(queryString));
            dbContext.addRowsCache(getPrimaryValue(dto),dto);
            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> loadAll(){
        Map<String,Object> params = new HashMap<String,Object>();
        String queryString = converter.queryString("*",getTableName(),params);
        T dto = null;
        try {
            if (connector.executeQuery(queryString))
            {
                List<T> dtos = map(connector.getAllRows());
                return dtos;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> excuteQuery(Query query){
        Map<String,Object> params = new HashMap<String,Object>();
        T dto = null;
        try {
            if (connector.executeQuery(query.toString()))
            {
                List<T> dtos = map(connector.getAllRows());
                return dtos;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getForeignKeyFieldName(String refTableName){
        for (Map.Entry<String, Column> column: columns.entrySet()) {
            if(column.getValue().getPrimaryKey()){
                if(column.getValue().getRefTable().equals(refTableName)){
                    return column.getKey();
                }
            }
        }
        return null;
    }


    // Empty implementation:
    private List<T> findAllWithSqlParams(String query, HashMap<String, Object> sqlParams){
/*        connector.(sqlQuery, sqlParams);
        List<T> all = new List<T>();
        while (_sqlDriver.Read())
        {
            T res = GetInstance(_sqlDriver.GetReaderValue()[0]);
            object primaryKey = (object)((DomainObject)(object)res).getKey();
            if (unitOfWork.IsExisted(primaryKey))
            {
                all.Add((T)unitOfWork.GetObjectByKey(primaryKey));
            }
            else
            {
                all.Add(res);
            }
        }
        return all;*/
        return null;

    }


    public List<T> findAll(){
        String sqlQuery = converter.queryString(getPrimaryKey(), tableName, "", "", "", "");
        return findByQuery(sqlQuery);
    }


    public List<T> findAllWithValue(String field, Object value){
        HashMap<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put(field, value);
        String sqlQuery = converter.queryString(getPrimaryKey(), tableName, sqlParams);
        return findAllWithSqlParams(sqlQuery, sqlParams);
    }


    public T findOneWithValue(Object field, Object value){
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put((String)field,value);
        String query = converter.queryString(getPrimaryKey(),tableName,params);
        List<T> tmp = findAllWithSqlParams(query, params);
        if(tmp != null && tmp.size() > 0){
            return tmp.get(0);
        }
        return null;
    }


    // Error:
    private List<T> findByQuery(String query, HashMap<String, Object> params){
        return null;
    }

    private List<T> findByQuery(String sqlQuery) {
        return null;
    }

    public List<T> FindAll(String queryString, HashMap<String, Object> parameters)
    {
        return findByQuery(queryString, parameters);
    }

    public T GetInstance(Object key)
    {
        if(dbContext.isExisted(key)){
            return (T)dbContext.getRowByKey(key);
        }
        return null;
    }


    // Error
    public List<Object[]> ExecuteQuery(String queryString, String tableName, HashMap<String, Object> queryParameters) {
        return null;
    }

    public boolean save(T instance){
        TableAction tableAction = new InsertAction(columns,getTableName(),getPrimaryKey());
        Map<String,String> fieldValues = tableAction.getField(instance);
        String insertQuery = converter.insertString(getTableName(),fieldValues);
        dbContext.addRowsCache(getPrimaryValue(instance),instance);
        return connector.execute(insertQuery);
    }

    public boolean delete(T instance){
        TableAction tableAction = new DeleteAction(columns,getTableName(),getPrimaryKey());
        Map<String,String> fieldValues = tableAction.getField(instance);
        String deleteString = converter.deleteString(getTableName(),getPrimaryKey(),fieldValues);
        Object primaryKey = getPrimaryValue(instance);
        if (dbContext.isExisted(primaryKey))
        {
            dbContext.removeRowsCache(getPrimaryValue(instance));
        }
        return connector.execute(deleteString);
    }

    public boolean update(T instance){
        TableAction tableAction = new DeleteAction(columns,getTableName(),getPrimaryKey());
        Map<String,String> fieldValues = tableAction.getField(instance);
        String updateString = converter.updateString(getTableName(),getPrimaryKey(),fieldValues);
        Object primaryKey = getPrimaryValue(instance);
        if (dbContext.isExisted(primaryKey)){
            dbContext.removeRowsCache(primaryKey);
            dbContext.addRowsCache(primaryKey,instance);
        }
        return connector.execute(updateString);
    }

    public T map(Map<String, Object> row) throws SQLException {
        if (row == null) return null;
        try {
            T dto = (T) tobject.getConstructor().newInstance();
            for (Map.Entry<String, Object> entity : row.entrySet()) {
                if (entity.getValue() == null) {
                    continue;  // Don't set DBNULL
                }
                String column = entity.getKey();
                Field field = fields.get(column);
                if (field != null) {
                    field.set(dto, convertInstanceOfObject(entity.getValue()));
                }
            }
            return dto;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new SQLException("Problem with data Mapping. See logs.");
        }
    }

    public List<T> map(List<Map<String, Object>> rows) throws SQLException {
        List<T> list = new LinkedList<>();

        for (Map<String, Object> row : rows) {
            list.add(map(row));
        }

        return list;
    }

    private T convertInstanceOfObject(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }
    //get value primary
    public Object getPrimaryValue(Object obj) {
        Field field = null;
        try {
            field = obj.getClass().getDeclaredField(getPrimaryKey());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
