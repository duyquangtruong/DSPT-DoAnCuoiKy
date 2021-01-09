package main.jdbc;

import ReadXML.UtilDBTarget;
import TableT.Table.Table;
import main.IConvertToString.IConvertToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {
    static Session session = null;
    private IConvertToString iConvertToString;
    private SessionFactory sessionFactory = null;
    private ConnectionUtils conn = null;
    private Map<Class, Table> tables;

    private Session(String connectionConfig, String username, String password,SessionFactory sessionFactory ){
        this.sessionFactory = sessionFactory;
        iConvertToString = sessionFactory.getDbFactory().getDBAdapter();
        conn = new ConnectionUtils();
        conn.open(connectionConfig,username,password);
        tables = new HashMap<>();
    }
    private Session(UtilDBTarget dbAdapter, SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        iConvertToString = sessionFactory.getDbFactory().getDBAdapter();
        conn = new ConnectionUtils();
        conn.open(dbAdapter);
    }
    public IConvertToString getConvertToString(){
        return iConvertToString;
    };

    public  ConnectionUtils getConn(){
        return conn;
    };


    public static void openSession(UtilDBTarget dbAdapter, SessionFactory sessionFactory){
        if (session==null){
            session = new Session(dbAdapter,sessionFactory);
        }
        else {
            session.close();
            session = new Session(dbAdapter, sessionFactory);
        }
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static Session getSession(){
        return session;
    }


    private void close() {
        conn.close();
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
            Table<?> table = tables.get(clazz);
            //conn.executeQuery(iConvertToString.queryString("*"));
        }
        return null;
    }

}
