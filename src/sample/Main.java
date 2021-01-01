package sample;

import main.jdbc.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Main {
    static Session session;
    static ConnectionUtils utils;
    static Connection connection;
    static Statement  st = null;
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParserConfigurationException {

        System.out.println("Get connection ... ");
        Class t = DBFactory.class;
        DBFactory dbFactory = new SQLFactory();
        System.out.println(dbFactory.getClass());
        System.out.println(t);
        System.out.println("Done!");
    }

}
