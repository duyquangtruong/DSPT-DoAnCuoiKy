package main.jdbc;

import TableT.DataTypeMapper.DataTypeMapper;
import TableT.DataTypeMapper.MySQLDataTypeMapper;
import main.IConvertToString.DBAdapter;
import main.IConvertToString.MySqlAdapter;

public class MySQLFactory extends DBFactory {
    @Override
    public DBAdapter getDBAdapter() {
        return new MySqlAdapter();
    }

    @Override
    public DataTypeMapper getDBTypeMapper() {
        return new MySQLDataTypeMapper();
    }
}
