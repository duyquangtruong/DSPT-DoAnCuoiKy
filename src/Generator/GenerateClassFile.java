package Generator;

import ReadXML.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import com.sun.codemodel.JCodeModel;

import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;

public class GenerateClassFile {

    @SuppressWarnings("unchecked")
    public void Generate(String configPath,String className, String packageName) {
        UtilDBTarget config = new UtilDBAdapter(configPath); //"src/ReadXML/DBConfig.xml"
        UtilDB configInfo = config.getUtil();

        //Tao SQLTemplate va lay connection tu configInfo

        //Tu connection lay duoc Metadata cua Database


    }
}
