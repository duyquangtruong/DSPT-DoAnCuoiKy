package SQLQuery;

import SQLQuery.element.*;
import SQLQuery.element.Select;

public class Query {
    private Select select;
    private String where;
    private GroupBy groupBy;
    private Having having;
    private OrderBy orderBy;

    public Query(Select select, String where, GroupBy groupBy, Having having, OrderBy orderBy) {
        this.select = select;
        this.where = where;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return SQLBuilderHelper._converter.queryString(select.getParamsToString(),select.getTablesToString(),where,orderBy.getParamsToString()
        ,groupBy.getParamsToString(),having.getParamsToString());
    }

    public String getSelect() {
        return select.getParamsToString();
    }

    public String getFrom() {
        return select.getTablesToString();
    }

    public String getWhere() {
        return where;
    }
    public String getGroupBy(){
        return groupBy.getParamsToString();
    }

    public String getHaving() {
        return having.getParamsToString();
    }

    public String getOrderBy() {
        return orderBy.getTablesToString();
    }
}
