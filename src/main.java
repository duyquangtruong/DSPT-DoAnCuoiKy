import Generator.GenerateClassFile;
import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import main.constants.Function;
import main.jdbc.Configuration;
import main.jdbc.Session;
import main.jdbc.SessionFactory;

import java.sql.SQLException;
import java.util.List;
import DAO.*;

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

//        //Query normal
//        hocsinh obj = (hocsinh) session.load(hocsinh.class,"H002");
//        System.out.println(String.format("classId: %s \n FirstName: %s",obj.getClassId(),obj.getFirstName()));
//
//        List<hocsinh> obs = session.loadAll(hocsinh.class);
//        List<Object> obs2 = session.excuteQuery("SELECT * FROM hocsinh");

//        hocsinh hocSinh = new hocsinh();
//        hocSinh.setId("H009");
//        hocSinh.setFirstName("Demo");
//        hocSinh.setClassId("C003");
//        hocSinh.setLastName("Name");
//        hocSinh.setCity("Da Nang");
//
//        //Save or create
//        session.save(hocSinh);

        //Update
//        obj.setCity("HN");
//        session.update(obj);

            //Query builder
        IQueryBuilder queryBuilder = sessionFactory.getDbFactory().getQueryBuider();
        queryBuilder
                .select()
                .from("hocsinh")
                .whereEqualValue("Firstname","Demo");
        Query query = queryBuilder.build();
        List<hocsinh> hocsinhs = session.queryQuey(hocsinh.class,query);


    }
}
