import Generator.GenerateClassFile;
import main.jdbc.Configuration;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.buildSessionFactory();
        GenerateClassFile generateClassFile = new GenerateClassFile();
        generateClassFile.Generate(configuration.getDbTarget());
    }
}
