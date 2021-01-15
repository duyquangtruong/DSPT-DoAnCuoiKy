import Generator.GenerateClassFile;
import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import SQLQuery.SQLBuilderHelper;
import SQLQuery.element.Select;
import SQLQuery.type.MySQL;
import main.constants.Function;
import main.jdbc.Configuration;
import main.jdbc.Session;
import main.jdbc.SessionFactory;

import java.sql.SQLException;
import java.util.List;

import DAO.first_table;
public class main {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
//        first_table object = (first_table) session.load(first_table.class,"hah");
//        List<first_table> obs = session.loadAll(first_table.class);
//        List<Object> obs2 = session.excuteQuery("SELECT * FROM first_table");
//        System.out.println(obs2);

        IQueryBuilder queryBuilder = new MySQL();
        queryBuilder
                .select("ID","HOTEN")
                .from("HOCSINH","LOPHOC")
                .whereEqualValue("HOTEN","Nguyen Van A")
                .groupBy("HOCSINH","HOTEN")
                .having("HOCSINH","HOTEN", Function.COUNT,"=","DUY")
                .orderBy("ID");
        Query query = queryBuilder.build();
        String SQLQuery = query.toString();
        System.out.println(SQLQuery);
        System.out.println("select: "+ query.getSelect());
        System.out.println("from " + query.getFrom());

        /*
        first_table ft = new first_table();
        ft.setHah("test12");
        ft.setField_2(123);
        ft.setTestrequired("120");
        session.update(ft);
        session.close();*/
        // Generate file
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        configuration.buildSessionFactory();
//        GenerateClassFile generateClassFile = new GenerateClassFile();
//        generateClassFile.Generate(configuration.getDbTarget());
    }
}
