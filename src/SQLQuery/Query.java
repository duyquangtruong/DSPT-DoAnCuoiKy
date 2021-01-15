package SQLQuery;

import SQLQuery.element.*;
import SQLQuery.element.Select;

public class Query {
    private String select;
    private String where;
    private String from;
    private String groupBy;
    private String having;
    private String orderBy;

    private String formattedQuery;


    public Query(String select, String from, String where, String groupBy, String having, String orderBy,String formattedQuery) {
        this.select = select;
        this.where = where;
        this.from = from;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
        this.formattedQuery = formattedQuery;
    }

    @Override
    public String toString() {
        return formattedQuery;
    }

    public String getSelect() {
        return select;
    }

    public String getFrom() {
        return from;
    }

    public String getWhere() {
        return where;
    }
    public String getGroupBy(){
        return groupBy;
    }

    public String getHaving() {
        return having;
    }

    public String getOrderBy() {
        return orderBy;
    }
}
