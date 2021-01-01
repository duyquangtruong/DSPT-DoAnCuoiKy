package main.jdbc;

public class SessionFactory {
    Session session = null;

    public SessionFactory(String config,String username, String password,String nameDB){
        Session.openSession(config,username,password,nameDB);
        session = Session.session;
    }
    public Session openSession(){
        return session;
    }
}
