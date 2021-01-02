package main.jdbc;

import main.IConvertToString.DBAdapter;

public abstract class DBFactory {

    public static DBFactory getDBFactory(String nameDB){
        if (nameDB=="sql"){
            return new SQLFactory();
        }
        if (nameDB=="mysql"){
            return new MySQLFactory();
        }
        return new MySQLFactory();
    }

    public abstract DBAdapter getDBAdapter();
}
