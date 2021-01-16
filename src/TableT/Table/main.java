package TableT.Table;

import main.jdbc.Configuration;
import main.jdbc.Session;
import main.jdbc.SessionFactory;

public class main {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.buildSessionFactory();
        Table hs = new Table(HocSinh.class);
        //System.out.println(hs.getPrimaryKey());
    }
}
