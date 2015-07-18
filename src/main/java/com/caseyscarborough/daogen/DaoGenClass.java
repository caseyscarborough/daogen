package com.caseyscarborough.daogen;

import java.util.ArrayList;
import java.util.List;

public class DaoGenClass {

  private static final String BIND_VARIABLE_CHARACTER = "?";
  private static final String SEPARATOR = ",";

  private List<DaoGenField> fields;
  private String className;
  private String tableName;

  public DaoGenClass() {
    this.fields = new ArrayList<DaoGenField>();
  }

  public void addToColumns(DaoGenField column) {
    fields.add(column);
  }

  public String getClassName() {
    return className;
  }

  public String getVariableName() {
    return className.substring(0, 1).toLowerCase() + className.substring(1);
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName.toUpperCase();
  }

  public String getColumnsList() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (DaoGenField field : fields) {
      if (!first) {
        sb.append(SEPARATOR + " ");
      }
      sb.append(field.getColumnName());
      first = false;
    }
    return sb.toString();
  }

  public String getBindValuesList() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (DaoGenField ignored : fields) {
      if (!first) {
        sb.append(SEPARATOR + " ");
      }
      sb.append(BIND_VARIABLE_CHARACTER);
      first = false;
    }
    return sb.toString();
  }

  public DaoGenField getIdColumn() {
    for (DaoGenField field : fields) {
      if (field.isIdColumn()) {
        return field;
      }
    }
    return null;
  }

  public String getIdColumnString() {
    DaoGenField idColumn = getIdColumn();
    if (idColumn != null) {
      return idColumn.getColumnName();
    }
    return null;
  }

  public String getIdColumnClass() {
    DaoGenField idColumn = getIdColumn();
    if (idColumn != null) {
      return idColumn.getType();
    }
    return null;
  }

  public String getResultSetSetters() {
    StringBuilder sb = new StringBuilder();
    for (DaoGenField field : fields) {
      sb.append(field.getResultSetSetter());
    }
    return sb.toString();
  }

  public String getFieldDeclarations() {
    StringBuilder sb = new StringBuilder();
    for (DaoGenField field : fields) {
      sb.append("    ").append(field.getFieldDeclaration()).append("\n");
    }
    return sb.toString();
  }

  public String getFieldList() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (DaoGenField field : fields) {
      if (!first) {
        sb.append(SEPARATOR + " ");
      }
      sb.append(field.getDeclaration());
      first = false;
    }
    return sb.toString();
  }

  public String getFieldConstructorSetters() {
    StringBuilder sb = new StringBuilder();
    for (DaoGenField field : fields) {
      sb.append("        ").append(field.getConstructorSetter()).append("\n");
    }
    return sb.toString();
  }

  public String getFieldGettersAndSetters() {
    StringBuilder sb = new StringBuilder();
    for (DaoGenField field : fields) {
      sb.append(field.getGetterMethod());
      sb.append(field.getSetterMethod());
    }
    return sb.toString();
  }

  public String getToString() {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    for (DaoGenField field : fields) {
      sb.append("            \"");
      if (i != 0) {
        sb.append(SEPARATOR + " ");
      }
      sb.append(field.getToString()).append("\n");
      i++;
    }
    return sb.toString();
  }

  public String getBindSetters() {
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (DaoGenField field : fields) {
      sb.append("            ").append(field.getBindSetter(i)).append("\n");
      i++;
    }
    return sb.toString();
  }

  public String getUpdateSetters() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (DaoGenField field : fields) {
      if (!first) {
        sb.append(SEPARATOR + " ");
      }
      sb.append(field.getColumnName() + " = " + BIND_VARIABLE_CHARACTER);
      first = false;
    }
    return sb.toString();
  }

  public String getBindUpdateSetters() {
    StringBuilder sb = new StringBuilder();
    sb.append(getBindSetters());
    sb.append("            ");
    sb.append(getIdColumn().getBindSetter(fields.size() + 1));
    return sb.toString();
  }
}
