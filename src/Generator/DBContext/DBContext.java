package Generator.DBContext;


import java.util.*;

public class DBContext<T> {
    // Cac HashMap se Hash theo ID cua object de lay data Ex: <id,object data>
    private Map<Object,T> rowsCache = new HashMap<Object,T>();

    public boolean isExisted(Object key){
        if(key == null){
            return false;
        }
        return rowsCache.containsKey(key);
    }

    public Object getRowByKey(Object key){
        if(!rowsCache.containsKey(key)){
            return null;
        }
        return rowsCache.get(key);
    }


    // Object duoc tao moi chua co trong db
    // Sau khi session luu nhung thay doi se submit du lieu moi o day vao db
    public boolean addRowsCache(Object primaryKey,T obj){
        if(obj == null){
            return false;
        }

        if(rowsCache.containsKey(primaryKey)){ // Kiem tra lai mot lan nua
            return false;
        }

        rowsCache.put(primaryKey,obj);

        return true;
    }

    public boolean removeRowsCache(Object primaryKey){
        if(primaryKey == null){
            return false;
        }

        rowsCache.remove(primaryKey);

        return true;
    }

    public List<T> getRowsCache(){
        return (List<T>) rowsCache.values();
    }


    public Boolean clearChanges(){
        rowsCache.clear();
        return true;
    }
}
