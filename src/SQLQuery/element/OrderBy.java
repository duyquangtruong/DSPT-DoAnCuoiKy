package SQLQuery.element;

import SQLQuery.SQLBuilderHelper;

public class OrderBy extends SQLBuilderHelper {

    public OrderBy() {
    }

    @Override
    protected String paramToQuery() {
        return "ORDER BY " + String.join(", ", _queryParamList);
    }
}
