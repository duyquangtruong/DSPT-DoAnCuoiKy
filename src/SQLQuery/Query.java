package SQLQuery;

public class Query {
    private Select select;
    private Where where;
    private Join join;
    private GroupBy groupBy;
    private Having having;
    private OrderBy orderBy;


    public Query(Select select, Where where, Join join, GroupBy groupBy, Having having, OrderBy orderBy) {
        this.select = select;
        this.where = where;
        this.join = join;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
