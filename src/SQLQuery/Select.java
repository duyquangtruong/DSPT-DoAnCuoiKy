package SQLQuery;

// SELECT Statement in query:
public class Select extends SQLBuilderHelper {

    public Select() {
    }

    public void addTable(String tableName){
        _queryParamList.add(String.format("%s", _converter.table(tableName)));
    }

    @Override
    protected String paramToQuery() {
        return String.join(", ",_queryParamList);
    }

    // get string to fetch all data when don't have any param:
    public String getAll(String tableName){
        if(_queryParamList.isEmpty()){
            return String.format("%s.*",tableName);
        }
        else{
            return String.join(", ", _queryParamList);
        }
    }

    public void addSelection(String tableName, String tableField){
        _queryParamList.add(_converter.field(tableName,tableField));
    }
}
