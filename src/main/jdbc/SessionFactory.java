package main.jdbc;

import TableT.Table.Table;

import java.util.Map;

public class SessionFactory {
    Session session = null;

    private Map<Class, Table> tables;

    public SessionFactory(String config,String username, String password,String nameDB,Map<Class, Table> tables){
        Session.openSession(config,username,password,nameDB);
        session = Session.session;
        this.tables = tables;
    }
    public Session openSession(){
        return session;
    }
}
