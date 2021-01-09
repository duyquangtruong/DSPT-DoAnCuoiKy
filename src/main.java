import Generator.GenerateClassFile;
import main.jdbc.Configuration;
import main.jdbc.Session;
import main.jdbc.SessionFactory;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.List;

public class main {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<Object[]> list = session.createQuery("Select * from first_table");
        list.forEach(o -> System.out.println(o));
        // Generate file
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        configuration.buildSessionFactory();
//        GenerateClassFile generateClassFile = new GenerateClassFile();
//        generateClassFile.Generate(configuration.getDbTarget());
    }
}
