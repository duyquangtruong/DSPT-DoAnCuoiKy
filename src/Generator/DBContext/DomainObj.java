package Generator.DBContext;

import main.jdbc.Session;

public abstract class DomainObj {
    protected Object primarykey;

    public Object getPrimarykey() {
        return primarykey;
    }
}
