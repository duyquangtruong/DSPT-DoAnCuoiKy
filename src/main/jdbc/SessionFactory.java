package main.jdbc;

import Generator.DBMapper.DBMapper;
import ReadXML.UtilDBAdapter;
import ReadXML.UtilDBTarget;
import TableT.Table.Table;

import java.util.HashMap;
import java.util.Map;

public class SessionFactory {

    private DBMapper dbMapper = null;
    private Map<Class,Table> tableMap;
    private DBFactory dbFactory;
    UtilDBTarget dbAdapter;

    public SessionFactory(UtilDBTarget dbAdapter){
        this.dbFactory = DBFactory.getDBFactory(dbAdapter.getUtil().getDBName());
        this.dbAdapter = dbAdapter;
        openSession();
        this.dbMapper = new DBMapper(Session.session);
        this.tableMap = new HashMap<>();
    }

    public Table getTable(Class classT){
        if (tableMap.containsKey(classT)) {
            return tableMap.get(classT);
        } else {
            if (setTable(classT)){
                return tableMap.get(classT);
            }
        }
        return null;
    }

    public Table getTable(String classT){
        for (Map.Entry<Class,Table> entry: tableMap.entrySet()
             ) {
            if (entry.getValue().getTableName().equals(classT)){
                return entry.getValue();
            }
        }
        return null;
    }


    public boolean setTable(Class classT){
        Table table = new Table(classT);
        if (tableMap.containsKey(classT)){
            tableMap.replace(classT,table);
            return true;
        }
        else if (checkHasTableInDB(table)) {
            tableMap.put(classT,table);
            return true;
        }
        return false;
    }

    public boolean checkHasTableInDB(Table table){
        return dbMapper.checkNameTable(table.getTableName());
    }

    public Session openSession(){
        return Session.openSession(dbAdapter, this);
    }

    public DBFactory getDbFactory() {
        return dbFactory;
    }
}
