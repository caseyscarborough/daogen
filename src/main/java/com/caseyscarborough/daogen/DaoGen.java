package com.caseyscarborough.daogen;

import java.util.ArrayList;
import java.util.List;

public class DaoGen {

  private List<DaoColumn> columns;
  private String className;
  private String tableName;
  private String packageName;
  private String databaseName;

  public DaoGen() {
    this.columns = new ArrayList<DaoColumn>();
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public List<DaoColumn> getColumns() {
    return columns;
  }

  public void setColumns(List<DaoColumn> columns) {
    this.columns = columns;
  }

  public void addToColumns(DaoColumn column) {
    columns.add(column);
  }

  public String getClassName() {
    return className;
  }

  public String getLowercaseClassName() {
    return className.substring(0, 1).toLowerCase() + className.substring(1);
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getTableName() {
    return databaseName + "." + tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName.toUpperCase();
  }

  public String getColumnsList() {
      StringBuilder sb = new StringBuilder();
      boolean first = true;
      for (DaoColumn column : columns) {
        if (!first) {
          sb.append(", ");
        }
        sb.append(column.getColumnName());
        first = false;
      }
      return sb.toString();
  }

  public String getBindValuesList() {
      StringBuilder sb = new StringBuilder();
      boolean first = true;
      for (DaoColumn ignored : columns) {
        if (!first) {
          sb.append(", ");
        }
        sb.append("?");
        first = false;
      }
      return sb.toString();
  }

  public DaoColumn getIdColumn() {
    for (DaoColumn column : columns) {
      if (column.isIdColumn()) {
        return column;
      }
    }
    return null;
  }

  public String getIdColumnString() {
    DaoColumn idColumn = getIdColumn();
    if (idColumn != null) {
      return idColumn.getColumnName();
    }
    return null;
  }

  public String getIdColumnClass() {
    DaoColumn idColumn = getIdColumn();
    if (idColumn != null) {
      return idColumn.getType();
    }
    return null;
  }

  public String getResultSetSetters() {
    StringBuilder sb = new StringBuilder();
    for (DaoColumn column: columns) {
      sb.append(this.getLowercaseClassName());
      sb.append(".set").append(column.getUpperCaseFieldName());
      sb.append("(resultSet.get" + column.getResultSetType() + "(\"" + column.getColumnName() + "\"));\n        ");
    }
    return sb.toString();
  }

  public String getFieldDeclarations() {
    StringBuilder sb = new StringBuilder();
    for (DaoColumn column: columns) {
      sb.append("    private " + column.getType() + " " + column.getFieldName() + ";\n");
    }
    return sb.toString();
  }

  public String getFieldList() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (DaoColumn column: columns) {
      if (!first) {
        sb.append(", ");
      }
      sb.append(column.getType() + " " + column.getFieldName());
      first = false;
    }
    return sb.toString();
  }

  public String getFieldConstructorSetters() {
    StringBuilder sb = new StringBuilder();
    for (DaoColumn column: columns) {
      sb.append("        this." + column.getFieldName() + " = " + column.getFieldName() + ";\n");
    }
    return sb.toString();
  }

  public String getFieldGettersAndSetters() {
    StringBuilder sb = new StringBuilder();
    for (DaoColumn column: columns) {
      sb.append("\n    public " + column.getType() + " get" + column.getUpperCaseFieldName() + "() {\n        ");
      sb.append("return " + column.getFieldName() + ";\n    ");
      sb.append("}\n\n    ");
      sb.append("public void set" + column.getUpperCaseFieldName() + "(" + column.getType() + " " + column.getFieldName() + ") {\n        ");
      sb.append("this." + column.getFieldName() + " = " + column.getFieldName() + ";\n    ");
      sb.append("}\n");
    }
    return sb.toString();
  }

  public String getToString() {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    for (DaoColumn column : columns) {
      sb.append("            \"");
      if (i != 0) {
        sb.append(", ");
      }
      sb.append(column.getFieldName() + "='\" + " + column.getFieldName() + " + \"'\" +\n");
      i++;
    }
    return sb.toString();
  }

  public String getBindSetters() {
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (DaoColumn column: columns) {
      sb.append("            ");
      sb.append("statement.set" + column.getResultSetType() + "(" + i + ", " + getLowercaseClassName() + "." + "get" + column.getUpperCaseFieldName() + "());\n");
      i++;
    }
    return sb.toString();
  }

  public String getUpdateSetters() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (DaoColumn column: columns) {
      if (!first) {
        sb.append(", ");
      }
      sb.append(column.getColumnName() + " = ?");
      first = false;
    }
    return sb.toString();
  }

  public String getBindUpdateSetters() {
    StringBuilder sb = new StringBuilder();
    sb.append(getBindSetters());
    sb.append("            ");
    sb.append("statement.set" + getIdColumn().getResultSetType() + "(" + (columns.size() + 1) + ", " + getLowercaseClassName() + ".get" + getIdColumn().getUpperCaseFieldName() + "());\n");
    return sb.toString();
  }
}
