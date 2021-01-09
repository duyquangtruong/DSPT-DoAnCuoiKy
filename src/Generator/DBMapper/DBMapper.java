package Generator.DBMapper;
import  main.jdbc.*;

import java.sql.Array;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class DBMapper {

    public static final String COL_DESC_PK_NAME = "PK_NAME";
    public static final String COL_DESC_FKCOLUMN_NAME = "FKCOLUMN_NAME";
    public static final String COL_DESC_PKTABLE_NAME = "PKTABLE_NAME";
    public static final String COL_DESC_PKCOLUMN_NAME = "PKCOLUMN_NAME";
    public static final String COL_DESC_COLUMN_NAME = "COLUMN_NAME";
    public static final String COL_DESC_DATA_TYPE = "DATA_TYPE";
    public static final String FOREIGN_INFO_KEY_TABLE = "Table";
    public static final String FOREIGN_INFO_KEY_COLUMN = "Column";

    private Session session = null;
    private DatabaseMetaData databaseMetaData = null;
    private ResultSet allTablesMetadatas = null;

    public DBMapper(Session sessionToMap) {
        session = sessionToMap;
        try {
            databaseMetaData = session.getConn().getDbConnection().getMetaData();

            allTablesMetadatas = databaseMetaData.getTables(null,null,null,new String[]{"TABLE"});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public  List<String> getAllTablesName() throws SQLException {
        if(allTablesMetadatas == null){
            return null;
        }

        List<String> result = new ArrayList<String>();

        for(int i=0;i<allTablesMetadatas.getMetaData().getColumnCount();i++){
            result.add(allTablesMetadatas.getMetaData().getTableName(i));
        }
        return result;
    }

    public List<String> getPrimaryKey(String tableName) throws SQLException {
        if(allTablesMetadatas == null){
            return null;
        }

        List<String> result = new ArrayList<String>();

        ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null,null,tableName);
        while(primaryKeys.next())
        {
            result.add(primaryKeys.getString(primaryKeys.getString(COL_DESC_PK_NAME)));
        }

        return result;
    }

    public  List<String> getForeignKey(String tableName) throws SQLException {
        if(databaseMetaData == null) {
            return null;
        }

        ResultSet resultSet = databaseMetaData.getImportedKeys(null,null,tableName);
        List<String> result = new ArrayList<String>();

        while(resultSet.next()){
            result.add(resultSet.getString(COL_DESC_FKCOLUMN_NAME));
        }

        return result;
    }

    /** Phuong thuc lay ten bang va thuoc tinh duoc tham chieu den.
     * @param tableName Ten bang chua khoa ngoai.
     * @param foreignKey Ten cua khoa ngoai.
     * @return Key: thong tin can lay ("Table"/"Column") Value: la gia tri cua key.
     */
    public HashMap<String,String> getForeignInfo(String tableName, String foreignKey) throws SQLException {
        if(databaseMetaData == null){
            return null;
        }

        ResultSet resultSet = databaseMetaData.getImportedKeys(null,null,tableName);
        HashMap<String,String> result = new HashMap<String,String>();

        while(resultSet.next()){
            if(resultSet.getString(COL_DESC_FKCOLUMN_NAME) == foreignKey)
            {
                result.put(FOREIGN_INFO_KEY_TABLE,resultSet.getString(COL_DESC_PKTABLE_NAME));
                result.put(FOREIGN_INFO_KEY_COLUMN,resultSet.getString(COL_DESC_PKCOLUMN_NAME));
            }
        }

        return result;
    }

    /**
     *
     * @return Key: Ten cot cua bang, Value: Kieu du lieu cua cot
     */
    public  HashMap<String,String> getTableAttributes(String tableName) throws SQLException {
        if(databaseMetaData == null){
            return null;
        }

        HashMap<String,String> result = new HashMap<String,String>();
        ResultSet resultSet = databaseMetaData.getColumns(null,null,tableName,null);

        while(resultSet.next()){
            result.put(resultSet.getString(COL_DESC_COLUMN_NAME),resultSet.getString(COL_DESC_DATA_TYPE));
        }

        return result;
    }
}
