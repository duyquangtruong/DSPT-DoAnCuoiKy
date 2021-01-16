package main.jdbc;

import Generator.DBContext.DomainObj;
import ReadXML.UtilDBTarget;
import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import TableT.Table.Table;
import main.IConvertToString.IConvertToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Session<T> {
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


    public static Session openSession(UtilDBTarget dbAdapter, SessionFactory sessionFactory){
        if (session==null){
            session = new Session(dbAdapter,sessionFactory);
        }
        else {
            session.close();
            session = new Session(dbAdapter, sessionFactory);
        }
        return session;
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static Session getSession(){
        return session;
    }


    public void close() {
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

    public Object load(Class clazz, Object id){
        Table table = sessionFactory.getTable(clazz);
        if (table != null){
            return table.load(id);
        }
        return null;
    }

    public List<T> loadAll(Class clazz){
        Table table = sessionFactory.getTable(clazz);
        if (table != null){
            return table.loadAll();
        }
        return null;
    }

    public List<Map<String,Object>> excuteQuery(String sqlQuery){
        if (conn.executeQuery(sqlQuery)){
            return conn.getAllRows();
        }
        return null;
    }


    public boolean save(T instance){
        Table table = sessionFactory.getTable(instance.getClass());
        if (table != null){
            return table.save(instance);
        }
        return false;
    }

    public boolean delete(T instance){
        Table table = sessionFactory.getTable(instance.getClass());
        if (table != null){
            return table.delete(instance);
        }
        return false;
    }

    public boolean update(T instance){
        Table table = sessionFactory.getTable(instance.getClass());
        if (table != null){
            return table.update(instance);
        }
        return false;
    }

    public boolean save(List<T> instances){
        if (instances == null)
        {
           return false;
        }
        if (instances.isEmpty()){
            return true;
        }
        Table table = sessionFactory.getTable(instances.get(0).getClass());
        if (instances != null){
            return table.save(instances);
        }
        return false;
    }

    public boolean delete(List<T> instances){
        if (instances == null)
        {
            return false;
        }
        if (instances.isEmpty()){
            return true;
        }
        Table table = sessionFactory.getTable(instances.getClass());
        if (table != null){
            return table.delete(instances);
        }
        return false;
    }

    public boolean update(List<T> instances){
        if (instances == null)
        {
            return false;
        }
        if (instances.isEmpty()){
            return true;
        }
        Table table = sessionFactory.getTable(instances.getClass());
        if (table != null){
            return table.update(instances);
        }
        return false;
    }


    public List queryQuey(Query query){
        Table table = sessionFactory.getTable(query.getFrom());
        if (table != null){
            return table.excuteQuery(query);
        }
        conn.executeQuery(query.toString());
        return conn.getAllRows();
    }

    public List queryQuey(Class clazz, Query query){
        Table table = sessionFactory.getTable(clazz);
        if (table != null){
            return table.excuteQuery(query);
        }
        conn.executeQuery(query.toString());
        return conn.getAllRows();
    }
}
