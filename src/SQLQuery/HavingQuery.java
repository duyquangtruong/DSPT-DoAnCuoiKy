package SQLQuery;

import main.constants.Function;

public class HavingQuery extends SQLBuilder{

    public HavingQuery() {
    }

    public String addHaving(String tableName, String fieldName, Function function, String op, String param){
        _queryParamList.add(String.format("%s(%s.%s) %s %s", function.toString(), tableName, fieldName, op, _converter.parameter(param)));
        return param;
    }

    @Override
    protected String paramToQuery() {
        return "HAVING " + String.join(" ",_queryParamList);
    }
}
