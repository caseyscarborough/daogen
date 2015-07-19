package com.caseyscarborough.daogen;

public class Field {

  private boolean isIdField;
  private Class clazz;
  private String columnName;
  private String name;
  private String type;

  public Field(Class clazz) {
    this.clazz = clazz;
  }

  public Class getClazz() {
    return clazz;
  }

  public void setClazz(Class clazz) {
    this.clazz = clazz;
  }

  public boolean isIdField() {
    return isIdField;
  }

  public void setIdField(boolean isIdColumn) {
    this.isIdField = isIdColumn;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName.toUpperCase();
  }

  public String getCapitalizedName() {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getResultSetType() {
    if (type.equals("Integer")) {
      return "Int";
    }
    return type;
  }

  @Override
  public String toString() {
    return "Field{" +
        "isIdField=" + isIdField +
        ", columnName='" + columnName + '\'' +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
