package SQLQuery;

import main.IConvertToString.IConvertToString;

public class WHQuery extends SQLBuilder {

    public WHQuery() {
    }

    @Override
    public void addParam(String param) {
        if(_queryParamList.isEmpty() == false){
            super.addParam(param);
        }
    }

    public void beginExp(){
        addParam("(");
    }

    public void endExp(){
        addParam(")");
    }

    public void andExp(){
        addParam("AND");
    }

    public void orExp(){
        addParam("OR");
    }

    public void notExp(){
        addParam("NOT");
    }

    public String addFieldComparition(String tableName, String fieldName, String op, String paramID){
        String strCondition = String.format("%s %s %s",_converter.field(tableName,fieldName), op, _converter.parameter(paramID));
        _queryParamList.add(strCondition);
        return paramID;
    }

    public void addTwoFieldComparision(String leftTableName, String rightTableName, String op, String rightTableField, String leftTableField){
        String strCondition = String.format("%s %s %s", _converter.field(leftTableName,leftTableField),op, _converter.field(rightTableName, rightTableField));
        _queryParamList.add(strCondition);
    }

    public String addFieldComparisionWithLike(String tableName, String tableField, String fieldValue, String paramID){
        String strCondition = String.format("%s LIKE %s", _converter.field(tableName,tableField), _converter.parameter(paramID));
        _queryParamList.add(strCondition);
        return paramID;
    }

    public void AddFieldIsNull(String tableName, String fieldName){
        _queryParamList.add(String.format("%s IS NULL", _converter.field(tableName,fieldName)));
    }

    public void AddFieldIsNotNull(String tableName, String fieldName){
        _queryParamList.add(String.format("%s IS NOT NULL", _converter.field(tableName,fieldName)));
    }


    @Override
    protected String paramToQuery() {
        return "WHERE" + String.join("", _queryParamList);
    }
}
