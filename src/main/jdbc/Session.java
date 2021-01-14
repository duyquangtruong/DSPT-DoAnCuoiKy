package main.jdbc;

import ReadXML.UtilDBTarget;
import TableT.Table.Table;
import main.IConvertToString.IConvertToString;

import java.util.List;

public class Session {
    static Session session = null;
    private IConvertToString iConvertToString;
    private SessionFactory sessionFactory = null;
    private ConnectionUtils conn = null;

    private Session(String connectionConfig, String username, String password,SessionFactory sessionFactory ){
        this.sessionFactory = sessionFactory;
        iConvertToString = sessionFactory.getDbFactory().getDBAdapter();
        conn = new ConnectionUtils();
        conn.open(connectionConfig,username,password);
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
        Table table = sessionFactory.getTable(util.getClass());
        if (table!=null){

        }
        return true;
    }


    public Object get(Class clazz, Object id){
        Table table = sessionFactory.getTable(clazz);
        if (table != null){
            conn.executeQuery(iConvertToString.queryString("*"));
        }
        return null;
    }

}
