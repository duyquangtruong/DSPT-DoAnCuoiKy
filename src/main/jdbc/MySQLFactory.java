package main.jdbc;

import main.IConvertToString.DBAdapter;
import main.IConvertToString.MySqlAdapter;

public class MySQLFactory extends DBFactory {
    @Override
    public DBAdapter getDBAdapter() {
        return new MySqlAdapter();
    }
}
