package sample;

import Generator.GenerateClassFile;
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

public class Main {
    static Session session;
    static Connection connection;
    static Statement  st = null;
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        System.out.println("Reading Json File...");
        GenerateClassFile Generator = new GenerateClassFile();
        Generator.Generate("./src/Generator/input/input.json","GeneraClassFile","com.Generator");
        System.out.println("Generate completed");

        System.out.println("Get connection ... ");
        config();
        // Lấy ra đối tượng Connection kết nối vào database.
        try {

            DatabaseMetaData dbmd = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rsdb = dbmd.getTables(connection.getCatalog(), null, "%", types);
            st = connection.createStatement();
            ResultSet rs = null;
            while (rsdb.next()) {
                String tablename = rsdb.getString("TABLE_NAME");
                System.out.println("Table name: "+tablename);

                String sql = "select * from "+tablename;
                rs = st.executeQuery(sql);
                ResultSetMetaData metaData = rs.getMetaData();

                int rowCount = metaData.getColumnCount();
                System.out.println("Field  \tDataType");

                for (int i = 0; i < rowCount; i++) {
                    System.out.print(metaData.getColumnName(i + 1) + "  \t");
                    System.out.println(metaData.getColumnTypeName(i + 1));
                }
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }

    public static void config() throws ParserConfigurationException {
        String userDirectory = System.getProperty("user.dir");
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        File inputFile = new File(userDirectory+"/src/main/jdbc/config.xml");
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(inputFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("property");
        String DBName = "";
        String url = "";
        String username = "";
        String password = "";
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (temp==0){
                    DBName = eElement.getTextContent();
                }
                else if (temp==1) {
                    url = eElement.getTextContent();
                }
                else if (temp==2){
                    username = eElement.getTextContent();
                }
                else {
                    password = eElement.getTextContent();
                }
            }
        }

        if (DBName.contains("mysql")){
            session = new SessionMySQL();
        }
        else if (DBName.contains("mssql")){
            session = new SessionSQL();
        }
        connection = session.connectDB(url,username,password);
    }
}
