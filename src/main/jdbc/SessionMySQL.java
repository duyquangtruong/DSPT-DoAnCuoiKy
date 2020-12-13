package main.jdbc;

public class SessionMySQL extends Session {
    @Override
    public ConnectionUtils createSession() {
        return new MySQLConnectionUtil();
    }
}
