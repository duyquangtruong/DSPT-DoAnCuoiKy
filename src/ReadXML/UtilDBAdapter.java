package ReadXML;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UtilDBAdapter implements UtilDBTarget {

    private UtilDB myUtlilDB;
    private String xmlUrl;

    public UtilDBAdapter(String xmlUrl) {
        this.xmlUrl = xmlUrl;
    }
    private void parseDocument(Document dom) {
        // get the root element
        Element docEle = dom.getDocumentElement();

        // get a nodelist of elements
        NodeList nl = docEle.getElementsByTagName("DBInfo");
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            myUtlilDB = getUtilDB(el);

        }
    }

    /**
     * I take an employee element and read the values in, create an Employee object and return it
     */
    private UtilDB getUtilDB(Element empEl) {

        // for each <employee> element get text or int values of
        // name ,id, age and name
        String DBName = getTextValue(empEl, "DBName");
        String url = getTextValue(empEl, "url");
        String username = getTextValue(empEl, "username");
        String password = getTextValue(empEl, "password");
        // Create a new Employee with the value read from the xml nodes
        return new UtilDB(DBName, url, username, password);


    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }

    @Override
    public UtilDB getUtil() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

//            Document dom = db.parse("src/employees.xml");
            Document dom = db.parse(this.xmlUrl);
            parseDocument(dom);
//            printData();

        } catch (SAXException | IOException | ParserConfigurationException se) {
            se.printStackTrace();
        }
        return myUtlilDB;
    }
}