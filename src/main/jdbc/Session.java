package main.jdbc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.sql.Connection;

public abstract class Session {
    public abstract ConnectionUtils createSession();
    public Connection connectDB(String url, String username, String password) throws ParserConfigurationException {
        try {
            ConnectionUtils cntUtil = createSession();
            return cntUtil.connectDB(url, username, password);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
