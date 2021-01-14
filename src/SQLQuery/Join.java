package SQLQuery;

public class Join extends SQLBuilderHelper {

    public Join() {
    }

    public void addJoin(String orginTableName, String leftField, String joinTableName, String rightField){
        _queryParamList.add(String.format("JOIN %s ON %s = %s",
                _converter.table(orginTableName),
                _converter.field(orginTableName, leftField),
                _converter.field(joinTableName,rightField)));
    }

    @Override
    protected String paramToQuery() {
        return String.join(" ", _queryParamList);
    }
}
