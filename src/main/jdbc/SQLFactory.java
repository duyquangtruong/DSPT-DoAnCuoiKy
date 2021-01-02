package main.jdbc;

import main.IConvertToString.DBAdapter;
import main.IConvertToString.SQLAdapter;

public class SQLFactory extends DBFactory{
    @Override
    public DBAdapter getDBAdapter() {
        return new SQLAdapter();
    }
}
