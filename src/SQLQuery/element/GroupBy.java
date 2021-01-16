package SQLQuery.element;

import SQLQuery.SQLBuilderHelper;

public class GroupBy extends SQLBuilderHelper {

    public GroupBy() {
    }

    public void addGroupBy(String tableName, String fieldName){
        _queryParamList.add(_converter.field(tableName, fieldName));
    }

    public void addGroupBy(String fieldName){
        _queryParamList.add( fieldName);
    }

    @Override
    protected String paramToQuery() {
        return "GROUP BY " + String.join(", ", _queryParamList);
    }
}
