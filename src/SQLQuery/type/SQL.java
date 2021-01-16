package SQLQuery.type;

import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import main.constants.Function;

public class SQL implements IQueryBuilder {
    @Override
    public IQueryBuilder select(String... select) {
        return null;
    }

    @Override
    public IQueryBuilder whereEqualValue(String column, String value) {
        return null;
    }

    @Override
    public IQueryBuilder whereEqualValue(String column, int value) {
        return null;
    }

    @Override
    public IQueryBuilder whereEqualColumn(String column1, String column2) {
        return null;
    }

    @Override
    public IQueryBuilder whereGreater(String column, int value) {
        return null;
    }

    @Override
    public IQueryBuilder whereGreaterColumn(String column1, String column2) {
        return null;
    }

    @Override
    public IQueryBuilder whereGreaterOrEqual(String column, int value) {
        return null;
    }

    @Override
    public IQueryBuilder whereGreaterOrEqualColumn(String column1, String column2) {
        return null;
    }

    @Override
    public IQueryBuilder whereLessThan(String column, int value) {
        return null;
    }

    @Override
    public IQueryBuilder whereLessThanColumn(String column1, String column2) {
        return null;
    }

    @Override
    public IQueryBuilder whereLessThanOrEqual(String column, int value) {
        return null;
    }

    @Override
    public IQueryBuilder whereLessThanOrEqualColumn(String column1, String column2) {
        return null;
    }

    @Override
    public IQueryBuilder whereNotEqual(String column, int value) {
        return null;
    }

    @Override
    public IQueryBuilder whereNotEqualColumn(String column1, String column2) {
        return null;
    }

    @Override
    public IQueryBuilder from(String... joinQuery) {
        return null;
    }

    @Override
    public IQueryBuilder groupBy(String table, String groupBy) {
        return null;
    }

    @Override
    public IQueryBuilder groupBy(String groupBy) {
        return null;
    }

    @Override
    public IQueryBuilder having(String tableName, String fieldName, Function function, String op, String param) {
        return null;
    }

    @Override
    public IQueryBuilder having(String tableName, String fieldName, Function function, String op, String tableName2, String param) {
        return null;
    }

    @Override
    public IQueryBuilder having(String fieldName, Function function, String op, String param) {
        return null;
    }

    @Override
    public IQueryBuilder having(String fieldName, String op, String param) {
        return null;
    }


    @Override
    public IQueryBuilder orderBy(String... orderByQuery) {
        return null;
    }

    @Override
    public Query build() {
        return null;
    }
}
