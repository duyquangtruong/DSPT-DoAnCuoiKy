package SQLQuery;

import SQLQuery.element.*;
import SQLQuery.element.Select;

public interface IQueryBuilder {
    IQueryBuilder select(Select select);
    IQueryBuilder where(Where whereQuery);
    IQueryBuilder join(Join joinQuery);
    IQueryBuilder groupBy(GroupBy groupByQuery);
    IQueryBuilder having(Having havingQuery);
    IQueryBuilder orderBy(OrderBy orderByQuery);

    Query build();
}
