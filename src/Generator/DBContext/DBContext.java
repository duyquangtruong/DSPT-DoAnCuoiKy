package Generator.DBContext;


import java.util.*;

public class DBContext extends DomainObj{
    // Cac HashMap se Hash theo ID cua object de lay data Ex: <id,object data>
    private Map<Object,Object> newRows = new HashMap<Object,Object>();
    private Map<Object,Object> removedRows = new HashMap<Object,Object>();
    private Map<Object,Object> currentRows = new HashMap<Object,Object>();

    public boolean isExisted(Object key){
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
    public boolean addFromDB(DomainObj obj){
        if(obj == null){
            return false;
        }
        currentRows.put(obj.getPrimarykey(),obj);
        return true;
    }

    // Object duoc tao moi chua co trong db
    // Sau khi session luu nhung thay doi se submit du lieu moi o day vao db
    public boolean addNewRow(DomainObj obj){
        if(obj == null){
            return false;
        }

        if(currentRows.containsKey(obj.getPrimarykey())){ // Kiem tra lai mot lan nua
            return false;
        }
        newRows.put(obj.getPrimarykey(),obj);

        return true;
    }

    // Dua object vao danh sach xoa
    // Sau khi session luu nhung thay doi, cac object se bi xoa khoi db
    public boolean addRemovedRow(DomainObj obj){
        if(obj == null ){
            return false;
        }

        if(newRows.containsKey(obj.getPrimarykey())){
            newRows.remove(obj.getPrimarykey());
            return true;
        }

        if(removedRows.containsKey(obj.getPrimarykey())){
            return false;
        }

        if(currentRows.containsKey(obj.getPrimarykey())){
            currentRows.remove(obj.getPrimarykey());
        }

        removedRows.put(obj.getPrimarykey(),obj);

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

    public void log(){
        System.out.print(String.format(">> Retrieve object "));

        System.out.print("--- Existing object ---");
        for(Object obj : currentRows.entrySet()){
            System.out.print(obj);
        }

        System.out.print("--- Prepare to insert new object ---");
        for(Object obj : newRows.entrySet()){
            System.out.print(obj);
        }

        System.out.print("--- Delete object ---");
        for(Object obj : removedRows.entrySet()){
            System.out.print(obj);
        }
    }


}
