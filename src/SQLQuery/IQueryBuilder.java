package SQLQuery;

public interface IQueryBuilder {
    IQueryBuilder select(Select select);
    IQueryBuilder where(Where whereQuery);
    IQueryBuilder join(Join joinQuery);
    IQueryBuilder groupBy(GroupBy groupByQuery);
    IQueryBuilder having(Having havingQuery);
    IQueryBuilder orderBy(OrderBy orderByQuey);

    Query build();
}
