package com.caseyscarborough.daogen;

public class DaoColumn {

  private boolean isIdColumn;
  private String columnName;
  private String fieldName;
  private String type;

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

  public String getUpperCaseFieldName() {
    return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
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
    return "DaoColumn{" +
        "isIdColumn=" + isIdColumn +
        ", columnName='" + columnName + '\'' +
        ", fieldName='" + fieldName + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
