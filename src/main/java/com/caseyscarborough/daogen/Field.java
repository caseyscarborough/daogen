package com.caseyscarborough.daogen;

public class Field {

  private boolean isIdColumn;
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

  public boolean isIdColumn() {
    return isIdColumn;
  }

  public void setIdColumn(boolean isIdColumn) {
    this.isIdColumn = isIdColumn;
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
        "isIdColumn=" + isIdColumn +
        ", columnName='" + columnName + '\'' +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
