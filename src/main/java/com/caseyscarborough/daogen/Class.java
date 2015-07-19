package com.caseyscarborough.daogen;

import java.util.ArrayList;
import java.util.List;

public class Class {

  private static final String BIND_VARIABLE_CHARACTER = "?";
  private static final String SEPARATOR = ",";

  private List<Field> fields;
  private String name;
  private String tableName;

  public Class() {
    this.fields = new ArrayList<Field>();
  }

  public void addToColumns(Field column) {
    fields.add(column);
  }

  public List<Field> getFields() {
    return fields;
  }

  public String getName() {
    return name;
  }

  public String getVariableName() {
    return name.substring(0, 1).toLowerCase() + name.substring(1);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName.toUpperCase();
  }

  public Field getIdField() {
    for (Field field : fields) {
      if (field.isIdField()) {
        return field;
      }
    }
    return null;
  }

  public int getNumberOfFields() {
    return fields.size();
  }

  @Override
  public String toString() {
    return "Class{" +
        "fields=" + fields +
        ", name='" + name + '\'' +
        ", tableName='" + tableName + '\'' +
        '}';
  }
}
