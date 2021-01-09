package main.jdbc;

import ReadXML.UtilDBAdapter;
import ReadXML.UtilDBTarget;

public class Configuration {
    String baseURL = "src/DBconfig.xml";
    UtilDBTarget dbTarget;

    public void configure(){
        configure(baseURL);
    }

    public UtilDBTarget getDbTarget(){
        return dbTarget;
    }

    public void configure(String inputBaseUrl){
        dbTarget = new UtilDBAdapter(inputBaseUrl);
    }

    public SessionFactory buildSessionFactory() {
        return new SessionFactory(dbTarget);
    }
}
