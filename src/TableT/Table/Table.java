package TableT.Table;

import Generator.DBContext.DBContext;
import Generator.DBContext.DomainObj;
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
import DAO.first_table;

import main.IConvertToString.IConvertToString;
import main.jdbc.ConnectionUtils;
import main.jdbc.DBFactory;
import main.jdbc.Session;

import java.lang.reflect.Field;
import java.util.*;

public class Table<T> {
    private Class<T> tobject;
    private IConvertToString converter;
    private ConnectionUtils connector;

    UpdateAction updateAction = null;
    InsertAction insertAction = null;
    DeleteAction deleteAction = null;

    public Table(Class<T> tclass) {

        tobject = tclass;

        converter = Session.getSession().getConvertToString();
        connector = Session.getSession().getConn();
        mapTable();
    }

    public Class getTObject() {
        return tobject;
    }
    public ConnectionUtils getConnector(){return connector;}

    private String tableName;
    private String primaryKeyDB="";

    //DB Context
    DBContext dbContext = new DBContext();

    Map<String, Column> columns ;
    Map<String, String> hasOne ;
    Map<String, String> hasMany ;


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
//            System.out.println(column.getValue().getPrimaryKey());
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

    }

    public Object[] load(Object id){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put(getPrimaryKey(),id);
        String queryString = converter.queryString("*",getTableName(),params);
        return connector.excuteQueryRow(queryString);
    }
    // Not finish:
//    public void loadData(DomainObj obj){
//        if(dbContext.isExisted(obj.getKey()))
//            return;
//        HashMap<String, Object> sqlParams = new HashMap<String, Object>();
//        sqlParams.put(getPrimaryKey(), obj.getKey());
//        String sqlQuery = converter.queryString(getDataSQL(), tableName, sqlParams);
//      //  connector.executeQuery(sqlQuery, sqlParams);
//        boolean isExisted = false;
//
//        // Missing condition:
//        while(true){
//            int i = 0;
//            //Object[] reader = connector.GetReaderValue();
///*            foreach (string fieldName in columns.Keys)
//            {
//                PropertyInfo propertyInfo = obj.GetType().GetProperty(fieldName);
//                propertyInfo.SetValue(obj, Convert.ChangeType(reader[i++], propertyInfo.PropertyType));
//            }
//            foundInDB = true;*/
//        }
//
//        if (isExisted == true) //Neu tim thay trong DB, them vao DS nhung object da co
//            dbContext.addFromDB(obj);
//        else //Neu khong co trong DB, object moi, them vao DS nhung object moi
//            dbContext.addNewRow(obj);
//        // HasOne
//        if (hasOne.size() > 0)
//        {
//            for (Map.Entry<String, String> tmp : hasOne.entrySet())
//            {
//               /* Session session = Session.getSession();
//                MethodInfo method = session..GetMethod("Table").MakeGenericMethod(obj.GetType().GetProperty(tmp.Key).PropertyType);
//                var table = method.Invoke(session, null);
//
//                method = table.GetType().GetMethod("GetPrimaryKeyName");
//                var priCol = method.Invoke(table,null);
//
//                // get foreignKey
//
//                string fieldName = this.GetForeignKeyFieldName(tmp.Value);
//
//                object fieldValue = obj.GetType().GetProperty(fieldName).GetValue(obj);
//
//                method = table.GetType().GetMethod("FindOneHaveValue");
//                var list = method.Invoke(table, new object[] { priCol, fieldValue });
//
//                PropertyInfo propertyInfo = obj.GetType().GetProperty(tmp.Key);
//                propertyInfo.SetValue(obj, Convert.ChangeType(list, propertyInfo.PropertyType));*/
//            }
//        }
//
//        // has many
//        if (hasMany.size() > 0)
//        {
//            for (Map.Entry<String, String> tmp : hasMany.entrySet())
//            {
//                Session session = Session.getSession();
//           /*     MethodInfo method = session.GetType().GetMethod("Table").MakeGenericMethod(obj.GetType().GetProperty(tmp.Key).PropertyType.GetGenericArguments()[0]);
//                var table = method.Invoke(session, null);
//                method = table.GetType().GetMethod("GetRefColumnName");
//                var refCol = method.Invoke(table, new object[] { tableName });
//                method = table.GetType().GetMethod("FindAllHaveValue");
//                var list = method.Invoke(table, new object[] { refCol, obj.getKey() });
//
//                PropertyInfo propertyInfo = obj.GetType().GetProperty(tmp.Key);
//                propertyInfo.SetValue(obj, Convert.ChangeType(list, propertyInfo.PropertyType));*/
//            }
//        }
//    }


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

    // Error:
//    private List findByQuery(String query){
//        connector.executeQuery(query);
//        List rs = new ArrayList();
//
//        // Check reader.read():
//        while(true){
//            // get Instance of T
//            Class tmp = GetInstance(connector.getReaderValue()[0]);
//            rs.add(tmp);
//        }
//        return rs;
//    }

    public List<T> FindAll(String queryString, HashMap<String, Object> parameters)
    {
        return findByQuery(queryString, parameters);
    }


    // Error
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

    public void log(){
        System.out.print(String.format(">> Working at table: %s", tableName));
        dbContext.log();
    }

    private void doWork(List<Object> objs, TableAction action) {
        for(Object obj : objs){
            action.execute(obj);
        }
    }

    public void commit(){
        // Update
/*        if (updateAction == null)
        {
            updateAction = new UpdateAction(columns, connector, tableName, getPrimaryKey());
        }
        doWork(DBContext.getDirtyList(), updateAction);*/

        // Insert
        if (insertAction == null)
        {
            insertAction = new InsertAction(columns, connector, tableName, getPrimaryKey());
        }
        doWork(dbContext.getNewRowsList(), insertAction);

        // Delete
        if (deleteAction == null)
        {
            deleteAction = new DeleteAction(columns, connector, tableName, getPrimaryKey());
        }
        doWork(dbContext.getRemovedRowsList(), deleteAction);

        dbContext.clearChanges();
    }
}
