package SQLQuery;

import main.IConvertToString.IConvertToString;
import main.jdbc.Session;

import java.util.*;

public abstract class SQLBuilder {

    protected List<String> _queryParamList = new ArrayList<>();

    protected static IConvertToString _converter = Session.getSession().getConvertToString();

    protected abstract String paramToQuery();

    // Missing implementation:
    public static String getQueryString(){
        return null;
    }

    public void addParam(String param){
        _queryParamList.add(param);
    }

    public String getParams(){
        return (_queryParamList.isEmpty()) ? "" : paramToQuery();
    }

    public SQLBuilder() {
    }
}
