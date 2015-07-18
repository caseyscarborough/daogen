package com.caseyscarborough.daogen;

public class DaoGenField {

  private boolean isIdColumn;
  private DaoGenClass clazz;
  private String columnName;
  private String fieldName;
  private String type;

  public DaoGenField(DaoGenClass clazz) {
    this.clazz = clazz;
  }

  public DaoGenClass getClazz() {
    return clazz;
  }

  public void setClazz(DaoGenClass clazz) {
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

  public String getConstructorSetter() {
    return "this." + fieldName + " = " + fieldName + ";";
  }

  public String getGetterMethod() {
    return "\n" +
        "    public " + type + " get" + getUpperCaseFieldName() + "() {\n" +
        "        return " + fieldName + ";\n" +
        "    }\n";
  }

  public String getSetterMethod() {
    return "\n" +
        "    public void set" + getUpperCaseFieldName() + "(" + getDeclaration() + ") {\n" +
        "        " + getConstructorSetter() + "\n" +
        "    }\n";
  }

  public String getResultSetSetter() {
    return clazz.getVariableName() + ".set" + getUpperCaseFieldName() + "(resultSet.get" + getResultSetType() + "(\"" + getColumnName() + "\"));";
  }

  public String getFieldDeclaration() {
    return "private " + getDeclaration() + ";";
  }

  public String getDeclaration() {
    return type + " " + fieldName;
  }

  public String getToString() {
    return fieldName + "='\" + " + fieldName + " + \"'\" +";
  }

  public String getBindSetter(int index) {
    return "statement.set" + getResultSetType() + "(" + index + ", " + clazz.getVariableName() + ".get" + getUpperCaseFieldName() + "());";
  }
}
