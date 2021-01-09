package SQLQuery;

public class OrderByQuey extends SQLBuilder{

    public OrderByQuey() {
    }

    @Override
    protected String paramToQuery() {
        return "ORDER BY " + String.join(", ", _queryParamList);
    }
}
