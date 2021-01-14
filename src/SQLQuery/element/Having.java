package SQLQuery.element;

import SQLQuery.SQLBuilderHelper;
import main.constants.Function;

public class Having extends SQLBuilderHelper {

    public Having() {
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
