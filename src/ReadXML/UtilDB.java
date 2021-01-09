package ReadXML;

public class UtilDB {

    private String DBName;
    private String url;
    private String username = null;
    private String password = null;

    public String getDBName() {
        return DBName;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


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