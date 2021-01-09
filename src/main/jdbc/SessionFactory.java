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
    DBFactory dbFactory;

    public SessionFactory(UtilDBTarget dbAdapter){
        System.out.println(dbAdapter.getUtil().getDBName());
        dbFactory = DBFactory.getDBFactory(dbAdapter.getUtil().getDBName());
        Session.openSession(dbAdapter, this);
        dbMapper = new DBMapper(Session.session);
        tableMap = new HashMap<>();
    }


    public Session openSession(){
        return Session.session;
    }

    public DBFactory getDbFactory() {
        return dbFactory;
    }
}
