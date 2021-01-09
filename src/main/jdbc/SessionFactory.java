package main.jdbc;

import Generator.DBMapper.DBMapper;
import ReadXML.UtilDBAdapter;
import ReadXML.UtilDBTarget;
import TableT.Table.Table;

import java.util.HashMap;
import java.util.Map;

public class SessionFactory {

    DBMapper dbMapper = null;
    Map<Object,Table> tableMap;

    public SessionFactory(UtilDBTarget dbAdapter){
        Session.openSession(dbAdapter, this);
        dbMapper = new DBMapper(Session.session);
        tableMap = new HashMap<>();
    }


    public Session openSession(){
        return Session.session;
    }
}
