package main.jdbc;

import SQLQuery.IQueryBuilder;
import TableT.DataTypeMapper.DataTypeMapper;
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

    public abstract DataTypeMapper getDBTypeMapper();

    public abstract IQueryBuilder getQueryBuider();

    //public abstract BuiderDB getBuider();
}
