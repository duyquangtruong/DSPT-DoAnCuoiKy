package SQLQuery.type;

import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import SQLQuery.element.*;
import SQLQuery.element.Select;

public class MySQL implements IQueryBuilder {

    private String select = "";
    private String from = "";
    private String where = "";
    private String groupBy = "";
    private String having = "";
    private String orderBy = "";

    public IQueryBuilder select(String... select) {
        if(select.length <= 0){
            return this;
        }
        Boolean isFirstElement = true;
        for( String column : select) {
            if (column.equals("*")) {
                this.select = column;
                break;
            }
            if (!column.equals("")) {
                if (isFirstElement) {
                    this.select += column;
                }else {
                    this.select += ", ";
                    this.select += column;
                }
            }
        }

        return this;
    }

    @Override
    public IQueryBuilder whereEqualValue(String column,String value) {
        this.where = column + " = " + "\'"+value+"\'";
        return this;
    }

    @Override
    public IQueryBuilder whereEqualValue(String column, int value) {
        this.where = column + " = " + String.valueOf(value);
        return this;
    }

    @Override
    public IQueryBuilder whereEqualColumn(String column1, String column2) {
        this.where = column1 + " = " + column2;
        return this;
    }

    @Override
    public IQueryBuilder whereGreater(String column, int value) {
        this.where = column + " > " + String.valueOf(value);
        return this;
    }

    @Override
    public IQueryBuilder whereGreaterColumn(String column1, String column2) {
        this.where = column1 + " > " + column2;
        return this;
    }

    @Override
    public IQueryBuilder whereGreaterOrEqual(String column, int value) {
        this.where = column + " >= " + String.valueOf(value);
        return this;
    }

    @Override
    public IQueryBuilder whereGreaterOrEqualColumn(String column1, String column2) {
        this.where = column1 + " >= " + column2;
        return this;
    }

    @Override
    public IQueryBuilder whereLessThan(String column, int value) {
        this.where = column + " < " + String.valueOf(value);
        return this;
    }

    @Override
    public IQueryBuilder whereLessThanColumn(String column1, String column2) {
        this.where = column1 + " < " + column2;
        return this;
    }

    @Override
    public IQueryBuilder whereLessThanOrEqual(String column, int value) {
        this.where = column + " <= " + String.valueOf(value);
        return this;
    }

    @Override
    public IQueryBuilder whereLessThanOrEqualColumn(String column1, String column2) {
        this.where = column1 + " <= " + column2;
        return this;
    }

    @Override
    public IQueryBuilder whereNotEqual(String column, int value) {
        this.where = column + " <> " + String.valueOf(value);
        return this;
    }

    @Override
    public IQueryBuilder whereNotEqualColumn(String column1, String column2) {
        this.where = column1 + " <> " + column2;
        return this;
    }

    @Override
    public IQueryBuilder from(String from) {
        this.from = from;
        return this;
    }

    @Override
    public IQueryBuilder groupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    @Override
    public IQueryBuilder having(String having) {
        this.having = having;
        return this;
    }

    @Override
    public IQueryBuilder orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    @Override
    public Query build() {

        if(select == null || select.equals("")){
            return null;
        }
        if(where == null || where.equals("")){
            return null;
        }
        if(from == null || from.equals("")){
            return null;
        }

        String formattedQuery = "";
        formattedQuery += "SELECT " + this.select;
        formattedQuery += " FROM " + this.from;
        formattedQuery += " WHERE " + this.where;

        if(this.groupBy != null && !this.groupBy.equals("")){
            formattedQuery += " GROUP BY " + groupBy;
        }

        if(this.having == null || this.having.equals("") || this.orderBy == null || this.orderBy.equals("")) {
            return new Query(select, from, where, groupBy, "", "", formattedQuery);
        }

        formattedQuery += " HAVING " + orderBy;
        formattedQuery += " ORDER BY " + orderBy;


        return new Query(select, from, where, groupBy, having, orderBy, formattedQuery);
    }

}
