package SQLQuery.element;

import SQLQuery.SQLBuilderHelper;
import main.constants.Function;

public class Having extends SQLBuilderHelper {

    public Having() {
    }

    public String addHaving(String tableName, String fieldName, Function function, String op, String param){
        _queryParamList.add(String.format("%s(%s.%s) %s %s AND ",
                function.toString(),
                tableName, fieldName, op,
                _converter.parameter(param)));
        return param;
    }

    public String addHaving( String fieldName, Function function,  String param){
        _queryParamList.add(String.format("%s(%s) %s AND ",
                function.toString(),
                fieldName,
                _converter.parameter(param)));
        return param;
    }
    public String addHaving( String fieldName, String op,  String param){
        _queryParamList.add(String.format("%s %s %s AND ",
                fieldName,
                op,
                _converter.parameter(param)));
        return param;
    }

    public String addHaving(String fieldName, Function function, String op, String param){
        _queryParamList.add(String.format("%s(%s) %s %s AND ",
                function.toString(),
                 fieldName, op,
                _converter.parameter(param)));
        return param;
    }
    public String addHaving(String tableName, String fieldName, Function function, String op,String tableName2, String param){
        _queryParamList.add(String.format("%s(%s.%s) %s (%s.%s) AND ",
                function.toString(),
                tableName, fieldName, op,tableName2,
                _converter.parameter(param)));
        return param;
    }

    @Override
    protected String paramToQuery() {
        String having = ("HAVING " + String.join(" ",_queryParamList));
        return having.substring(0,having.length()-5);
    }
}
