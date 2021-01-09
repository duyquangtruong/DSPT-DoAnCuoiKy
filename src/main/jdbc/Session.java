package main.jdbc;

import main.IConvertToString.IConvertToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {
    static Session session = null;
    private static IConvertToString iConvertToString;
    private Map<Class,Object> tables;
    private ConnectionUtils conn = null;

    Session(String connectionConfig, String username, String password,String nameDB){
        DBFactory dbFactory = DBFactory.getDBFactory(nameDB);
        iConvertToString = dbFactory.getDBAdapter();
        conn = new ConnectionUtils();
        conn.open(connectionConfig,username,password);
        tables = new HashMap<>();
    }

    public static IConvertToString getConvertToString(){
        return iConvertToString;
    };

    public  ConnectionUtils getConn(){
        return conn;
    };

    Session(String connectionConfig,String nameDB){
        DBFactory dbFactory = DBFactory.getDBFactory(nameDB);
        iConvertToString = dbFactory.getDBAdapter();
        conn = new ConnectionUtils();
        conn.open(connectionConfig);
        tables = new HashMap<>();
    }

    public static void openSession(String config,String username, String password,String nameDB){
        if (session==null){
            session = new Session(config,username,password,nameDB);
        }
        else {
            session.close();
            session = new Session(config,username,password,nameDB);
        }
    }


    private void close() {
        conn.close();
    }

    public static void open(String config,String username, String password,String nameDB){
        if (session==null){
            session = new Session(config,username,password,nameDB);
        }
        else {
            session.close();
            session = new Session(config,username,password,nameDB);
        }
    }

    public List createSQLQuery( Class T,String query){
        conn.executeQuery(query);
        return conn.getAllRows();
    }

    public List createQuery(String query){
        conn.executeQuery(query);
        return  conn.getAllRows();
    }

    public boolean save(Object util){
        if (tables.containsKey(util.getClass())){

        }
        return true;
    }

    public Object get(Class clazz, Object id){
        if (tables.containsKey(clazz)){
            Object table = tables.get(clazz);
            //conn.executeQuery(iConvertToString.queryString("*"));
        }
        return null;
    }
}
