package ReadXML;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        UtilDBTarget domParser = new UtilDBAdapter("src/ReadXML/DBConfig.xml");
        System.out.println(domParser.getUtil().toString());
    }
}