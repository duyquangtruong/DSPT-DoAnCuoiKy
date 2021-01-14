package SQLQuery;

import main.IConvertToString.IConvertToString;
import main.jdbc.Session;

import java.util.*;

public abstract class SQLBuilderHelper {

    public SQLBuilderHelper() {

    }

    protected List<String> _queryParamList = new ArrayList<>();

    protected static IConvertToString _converter = Session.getSession().getConvertToString();

    protected abstract String paramToQuery();

/*    public static String getQueryString(Select selectQuery, Where whQuery, GroupBy groupQuery, Having havingQuery, OrderBy orderByQuey, Join joinQuery, String tableName){
        String src = String.format("%s %s", _converter.table(tableName), joinQuery.getParamsToString());
        return _converter.queryString(selectQuery.getParamsToString(), whQuery.getParamsToString(), src, orderByQuey.getParamsToString(), groupQuery.getParamsToString(), havingQuery.getParamsToString());
    }*/

    public void addParam(String param){
        _queryParamList.add(param);
    }

    public String getParamsToString(){
        return (_queryParamList.isEmpty()) ? "" : paramToQuery();
    }


}
