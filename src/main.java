import Generator.GenerateClassFile;
import main.jdbc.Configuration;

import java.sql.SQLException;
import java.text.Normalizer;

public class main {
    public static void main(String[] args) throws SQLException {
        /*Configuration configuration = new Configuration();
        configuration.configure();
        configuration.buildSessionFactory();
        GenerateClassFile generateClassFile = new GenerateClassFile();
        generateClassFile.Generate(configuration.getDbTarget());*/
        String convertedString = "test test";
        System.out.println(    Normalizer
                .normalize(convertedString, Normalizer.Form.NFD)
                .replaceAll("\\s+", "_"));
    }
}
