package TableT.Column;

public class Column {
    private String name;
    private String type;
    private Boolean isPrimaryKey;
    private Boolean isForeignKey;
    private Boolean isRequired;

    public String getName(){return name;}
    public String getType(){return type;}
    public Boolean IsRequired(){return isRequired;}
    public Boolean IsForeignKey(){return isForeignKey;}
    public Boolean IsPrimaryKey(){return isPrimaryKey;}

    public Column(String columnName,String columnType){
        name=columnName;
        type = columnType;
        isPrimaryKey=false;
        isForeignKey=false;
        isRequired=false;
    }

    public Column(String columnName,String columnType, Boolean primaryKey){
        name=columnName;
        type = columnType;
        isPrimaryKey=primaryKey;
        isForeignKey=false;
        if(primaryKey) {
            isRequired=true;
        }
        else {
            isRequired=false;
        }
    }

    public Column(String columnName,String columnType, Boolean foreignKey, Boolean requiredColumn){
        name=columnName;
        type = columnType;
        isPrimaryKey=false;
        isForeignKey=foreignKey;
        isRequired=requiredColumn;
    }
}
