package SQLQuery.type;

import SQLQuery.IQueryBuilder;
import SQLQuery.Query;
import SQLQuery.SQLBuilderHelper;
import SQLQuery.element.*;
import SQLQuery.element.Select;
import main.constants.Function;

public class SQL implements IQueryBuilder {

    private Select select = new Select();
    private String where =  "";
    private GroupBy groupBy = new GroupBy();
    private Having having = new Having();
    private OrderBy orderBy = new OrderBy();

    public IQueryBuilder select(String... select) {
        if(select.length <= 0){
            this.select.addParam("*");
            return this;
        }
        for( String column : select) {
            if (column.equals("*")) {
                this.select.addParam("*");
                break;
            }
            if (!column.equals("")) {
                this.select.addParam(column);
            }
        }
        return this;
    }

    @Override
    public IQueryBuilder from(String... tables) {
        if(tables.length <= 0){
            return this;
        }
        for( String table : tables) {
            if (!table.equals("")) {
                this.select.addTable(table);
            }
        }
        return this;
    }

    @Override
    public IQueryBuilder whereEqualValue(String column,String value) {
        this.where += column + " = " + "\'"+value+"\'" + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereEqualValue(String column, int value) {
        this.where += column + " = " + String.valueOf(value) + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereEqualColumn(String column1, String column2) {
        this.where += column1 + " = " + column2 + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereGreater(String column, int value) {
        this.where += column + " > " + String.valueOf(value) + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereGreaterColumn(String column1, String column2) {
        this.where += column1 + " > " + column2 + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereGreaterOrEqual(String column, int value) {
        this.where += column + " >= " + String.valueOf(value) + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereGreaterOrEqualColumn(String column1, String column2) {
        this.where += column1 + " >= " + column2 + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereLessThan(String column, int value) {
        this.where += column + " < " + String.valueOf(value) + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereLessThanColumn(String column1, String column2) {
        this.where += column1 + " < " + column2 + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereLessThanOrEqual(String column, int value) {
        this.where += column + " <= " + String.valueOf(value) + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereLessThanOrEqualColumn(String column1, String column2) {
        this.where += column1 + " <= " + column2 + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereNotEqual(String column, int value) {
        this.where += column + " <> " + String.valueOf(value) + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder whereNotEqualColumn(String column1, String column2) {
        this.where += column1 + " <> " + column2 + " AND ";
        return this;
    }

    @Override
    public IQueryBuilder groupBy(String table, String groupBy) {
        this.groupBy.addGroupBy(table,groupBy);
        return this;
    }

    @Override
    public IQueryBuilder groupBy(String groupBy) {
        this.groupBy.addGroupBy(groupBy);
        return this;
    }

    @Override
    public IQueryBuilder having(String tableName, String fieldName, Function function, String op, String param) {
        this.having.addHaving(tableName,fieldName,function,op,param);
        return this;
    }

    @Override
    public IQueryBuilder having(String tableName, String fieldName, Function function, String op, String tableName2, String param) {
        this.having.addHaving(tableName,fieldName,function,op,tableName2,param);
        return this;
    }

    @Override
    public IQueryBuilder having(String fieldName, Function function, String op, String param) {
        this.having.addHaving(fieldName,function,op,param);
        return this;
    }

    @Override
    public IQueryBuilder having(String fieldName, String op, String param) {
        this.having.addHaving(fieldName,op,param);
        return this;
    }

    @Override
    public IQueryBuilder orderBy(String... orderBy) {
        if(orderBy.length <= 0){
            return this;
        }
        for( String column : orderBy) {
            if (!column.equals("")) {
                this.orderBy.addParam(column);
            }
        }
        return this;
    }

    @Override
    public Query build() {

        if(select == null || select.equals("") || select.getTablesToString() == null || select.getTablesToString().equals("")) {
            return null;
        }
        this.where = where.substring(0,where.length()-5);
        return new Query(select, where, groupBy, having, orderBy);
    }

}
