package Generator.DBContext;


import java.util.*;

public class DBContext {
    // Cac HashMap se Hash theo ID cua object de lay data Ex: <id,object data>
    private HashMap<Object,Object> newRows = new HashMap<Object,Object>();
    private HashMap<Object,Object> removedRows = new HashMap<Object,Object>();
    private HashMap<Object,Object> currentRows = new HashMap<Object,Object>();

    public Boolean isExisted(Object key){
        if(key == null){
            return false;
        }
        return currentRows.containsKey(key);
    }

    public Object getRowByKey(Object key){
        if(!currentRows.containsKey(key)){
            return null;
        }
        return currentRows.get(key);
    }

    // Lazy loading
    // object sau khi duoc doc tu db len se duoc cache lai o day
    public Boolean addFromDB(DomainObj obj){
        if(obj == null){
            return false;
        }
        currentRows.put(obj.getKey(),obj);
        return true;
    }

    // Object duoc tao moi chua co trong db
    // Sau khi session luu nhung thay doi se submit du lieu moi o day vao db
    public Boolean addNewRow(DomainObj obj){
        if(obj == null){
            return false;
        }

        if(currentRows.containsKey(obj.getKey())){ // Kiem tra lai mot lan nua
            return false;
        }
        newRows.put(obj.getKey(),obj);

        return true;
    }

    // Dua object vao danh sach xoa
    // Sau khi session luu nhung thay doi, cac object se bi xoa khoi db
    public Boolean addRemovedRow(DomainObj obj){
        if(obj == null || !obj.isLoaded()){
            return false;
        }

        if(newRows.containsKey(obj.getKey())){
            newRows.remove(obj.getKey());
            return true;
        }

        if(removedRows.containsKey(obj.getKey())){
            return false;
        }

        if(currentRows.containsKey(obj.getKey())){
            currentRows.remove(obj.getKey());
        }

        removedRows.put(obj.getKey(),obj);

        return true;
    }

    public List<Object> getNewRowsList(){
        return (List<Object>) newRows.values();
    }

    public List<Object> getCurrentRowsList(){
        return (List<Object>) currentRows.values();
    }

    public List<Object> getRemovedRowsList(){
        return (List<Object>) removedRows.values();
    }

    public Boolean clearChanges(){
        newRows.clear();
        removedRows.clear();

        return true;
    }
}
