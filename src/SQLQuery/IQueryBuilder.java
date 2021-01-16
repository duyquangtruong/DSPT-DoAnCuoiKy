package SQLQuery;

import SQLQuery.element.*;
import SQLQuery.element.Select;
import main.constants.Function;

public interface IQueryBuilder {
    IQueryBuilder select(String... select);

    IQueryBuilder whereEqualValue(String column, String value);
    IQueryBuilder whereEqualValue(String column, int value);
    IQueryBuilder whereEqualColumn(String column1, String column2);

    IQueryBuilder whereGreater(String column, int value);
    IQueryBuilder whereGreaterColumn(String column1, String column2);

    IQueryBuilder whereGreaterOrEqual(String column, int value);
    IQueryBuilder whereGreaterOrEqualColumn(String column1, String column2);

    IQueryBuilder whereLessThan(String column, int value);
    IQueryBuilder whereLessThanColumn(String column1, String column2);

    IQueryBuilder whereLessThanOrEqual(String column, int value);
    IQueryBuilder whereLessThanOrEqualColumn(String column1, String column2);

    IQueryBuilder whereNotEqual(String column, int value);
    IQueryBuilder whereNotEqualColumn(String column1, String column2);

    IQueryBuilder from(String... joinQuery);
    IQueryBuilder groupBy(String table, String groupBy);
    IQueryBuilder groupBy(String groupBy);
    IQueryBuilder having(String tableName, String fieldName, Function function, String op, String param);
    IQueryBuilder having(String tableName, String fieldName, Function function, String op,String tableName2, String param);
    IQueryBuilder having(String fieldName, Function function,String op, String param);
    IQueryBuilder having(String fieldName, String op, String param);
    IQueryBuilder orderBy(String... orderByQuery);

    Query build();
}
