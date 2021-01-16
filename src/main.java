import Generator.GenerateClassFile;
import main.jdbc.Configuration;
import main.jdbc.Session;
import main.jdbc.SessionFactory;
import DAO.*;

import java.sql.SQLException;
import java.util.List;

public class main {
    public static void main(String[] args) throws SQLException {

        //Tạo file
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        configuration.buildSessionFactory();
//        GenerateClassFile generateClassFile = new GenerateClassFile();
//        generateClassFile.Generate(configuration.getDbTarget());

        //Query
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        hocsinh object = (hocsinh) session.load(hocsinh.class,"1");
        List<hocsinh> obs = session.loadAll(hocsinh.class);
        List<Object> obs2 = session.excuteQuery("SELECT * FROM HOCSINH");
        System.out.println(object.toString());

        /*
        IQueryBuilder queryBuilder = new MySQL();
        queryBuilder
                .select("ID","HOTEN")
                .from("HOCSINH","LOPHOC")
                .whereEqualValue("HOTEN","Nguyen Van A")
                .groupBy("HOCSINH","HOTEN")
                .having("HOCSINH","HOTEN", Function.COUNT,"=","DUY")
        Query query = queryBuilder.build();
        String SQLQuery = query.toString();
        System.out.println(SQLQuery);
        System.out.println("select: "+ query.getSelect());
        System.out.println("from " + query.getFrom());*/

        /*
        first_table ft = new first_table();
        ft.setHah("test12");
        ft.setField_2(123);
        ft.setTestrequired("120");
        session.update(ft);
        session.close();*/
        // Generate file
    }
}
