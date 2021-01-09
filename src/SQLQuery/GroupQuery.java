package SQLQuery;

public class GroupQuery extends SQLBuilder{

    public GroupQuery() {
    }

    public void addGroupBy(String tableName, String fieldName){
        _queryParamList.add(_converter.field(tableName, fieldName));
    }

    @Override
    protected String paramToQuery() {
        return "GROUP BY" + String.join(", ", _queryParamList);
    }
}
