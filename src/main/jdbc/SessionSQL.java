package main.jdbc;

public class SessionSQL extends Session {
    @Override
    public ConnectionUtils createSession() {
        return new SQLConnectionUtil();
    }
}
