package main.jdbc;

import SQLQuery.IQueryBuilder;
import SQLQuery.type.MySQL;
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

    @Override
    public IQueryBuilder getQueryBuider() {
        return new MySQL();
    }
}
