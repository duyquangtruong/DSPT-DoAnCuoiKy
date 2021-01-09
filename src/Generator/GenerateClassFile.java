package Generator;

import Generator.DBMapper.DBMapper;
import ReadXML.*;
import main.jdbc.ConnectionUtils;
import main.jdbc.Session;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import com.sun.codemodel.JCodeModel;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;
import java.sql.DatabaseMetaData;
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

        connection.open(configInfo.toString());

        //Truyen Session muon map database
        DBMapper dbMapper = new DBMapper(session);

        List<String> tableList = dbMapper.getAllTablesName();

        for(int i =0;i<tableList.size();i++){
            Writer.writeClass(tableList.get(i),dbMapper);
        }
    }
}
