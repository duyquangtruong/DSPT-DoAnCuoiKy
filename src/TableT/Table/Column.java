package TableT.Table;

public class Column {
    private String columnName;
    private String columnType;
    private Boolean isPrimaryKey;
    private Boolean isForeignKey;
    private Boolean isRequired;
    private String refTable;
    public Column()
    {
    }
    public Column(String columnName, String columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isPrimaryKey = false;
        this.isForeignKey = false;
        this.isRequired = false;
    }
    public Column(String columnName, String columnType, Boolean isRequired) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isPrimaryKey = false;
        this.isForeignKey = false;
        this.isRequired = isRequired;
    }
    public Column(String columnName, String columnType, Boolean isPrimaryKey, Boolean isRequired) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isPrimaryKey = isPrimaryKey;
        if(isPrimaryKey){
            this.isRequired=isPrimaryKey;
        }
        else {
            this.isRequired=isRequired;
        }
        this.isForeignKey = false;

    }
    public Column(String columnName, String columnType,String refTable) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isPrimaryKey = false;
        this.isForeignKey = true;
        this.isRequired = true;
        this.refTable = refTable;
    }


    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public Boolean getPrimaryKey() {
        return isPrimaryKey;
    }

    public Boolean getForeignKey() {
        return isForeignKey;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public String getRefTable() {
        return refTable;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", isPrimaryKey=" + isPrimaryKey +
                ", isForeignKey=" + isForeignKey +
                ", isRequired=" + isRequired +
                ", refTable='" + refTable + '\'' +
                '}';
    }
}
