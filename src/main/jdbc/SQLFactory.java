package main.jdbc;

import TableT.DataTypeMapper.DataTypeMapper;
import TableT.DataTypeMapper.SQLDataTypeMapper;
import main.IConvertToString.DBAdapter;
import main.IConvertToString.SQLAdapter;

public class SQLFactory extends DBFactory{
    @Override
    public DBAdapter getDBAdapter() {
        return new SQLAdapter();
    }

    @Override
    public DataTypeMapper getDBTypeMapper() {
        return new SQLDataTypeMapper();
    }
}
