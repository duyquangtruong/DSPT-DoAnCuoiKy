package ReadXML;

public class UtilDB {

    private String DBName;
    private String url;
    private String username;
    private String password;

    public UtilDB(String DBName, String url, String username, String password) {
        this.DBName = DBName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return DBName + ": " + url + ", age: " + username + ", type: " + password;
    }

}