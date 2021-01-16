package Generator;

import Generator.DBMapper.DBMapper;
import ReadXML.*;
import main.jdbc.ConnectionUtils;
import main.jdbc.Session;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class GenerateClassFile {

    @SuppressWarnings("unchecked")
    public void Generate(String configPath) throws SQLException {
        UtilDBTarget config = new UtilDBAdapter(configPath); //"src/ReadXML/DBConfig.xml"
        UtilDB configInfo = config.getUtil();

        Session session = Session.getSession();
        if(session == null) {
            return;
        }

        ConnectionUtils connection = session.getConn();
        if(connection == null){
            return;
        }

        //Truyen Session muon map database
        DBMapper dbMapper = new DBMapper(session);

        List<String> tableList = dbMapper.getAllTablesName();

        for(int i =0;i<tableList.size();i++){
            Writer.writeClass(tableList.get(i),dbMapper);
        }
    }

    public void Generate(UtilDBTarget utilDBTarget) throws SQLException {
        UtilDBTarget config = utilDBTarget;
        UtilDB configInfo = config.getUtil();

        Session session = Session.getSession();
        if(session == null) {
            return;
        }

        ConnectionUtils connection = session.getConn();
        if(connection == null){
            return;
        }


        //Truyen Session muon map database
        DBMapper dbMapper = new DBMapper(session);

        List<String> tableList = dbMapper.getAllTablesName();

        for(int i =0;i<tableList.size();i++){
            Writer.writeClass(tableList.get(i),dbMapper);
        }
    }
}
