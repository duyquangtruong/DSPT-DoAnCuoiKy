package main.jdbc;

import ReadXML.UtilDBAdapter;
import ReadXML.UtilDBTarget;

public class Configuration {
    String baseURL = "src/DBconfig.xml";
    UtilDBTarget dbAdapter;

    public void configure(){
        configure(baseURL);
    }

    public void configure(String inputBaseUrl){
        dbAdapter = new UtilDBAdapter(inputBaseUrl);
    }

    public SessionFactory buildSessionFactory() {
        return new SessionFactory(dbAdapter);
    }
}
