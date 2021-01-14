package SQLQuery.type;

import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import SQLQuery.element.*;
import SQLQuery.element.Select;

public class MySQL implements IQueryBuilder {

    private Select select;
    private Where where;
    private Join join;
    private GroupBy groupBy;
    private Having having;
    private OrderBy orderBy;

    public IQueryBuilder select(Select select) {
        this.select = select;
        return this;
    }

    @Override
    public IQueryBuilder where(Where where) {
        this.where = where;
        return this;
    }

    @Override
    public IQueryBuilder join(Join join) {
        this.join = join;
        return this;
    }

    @Override
    public IQueryBuilder groupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    @Override
    public IQueryBuilder having(Having having) {
        this.having = having;
        return this;
    }

    @Override
    public IQueryBuilder orderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    @Override
    public Query build() {
        return new Query(select, where, join, groupBy, having, orderBy);
    }

}
