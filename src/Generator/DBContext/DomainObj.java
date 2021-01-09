package Generator.DBContext;

import main.jdbc.Session;

public abstract class DomainObj {
    enum status {Ghost, Loaded}
    private status objStatus;
    private Object key;

    public Object getKey(){
        return key;
    }

    public DomainObj(){
        objStatus=status.Ghost;
    }

    public Boolean isLoaded(){
        return (objStatus == status.Loaded);
    }

    private void setStatusLoaded(){
        objStatus = status.Loaded;
    }

    private void setStatusGhost(){
        objStatus = status.Ghost;
    }

    private <T> void loadFromDB(){
        if(!isLoaded()){
//            Session session = Session.getSession();
            // Goi query tu session de lay du lieu

            setStatusLoaded();
        }
    }
}
