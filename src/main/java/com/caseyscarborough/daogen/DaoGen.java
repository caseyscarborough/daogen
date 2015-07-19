package com.caseyscarborough.daogen;

public class DaoGen {

  private String packageName;
  private String databaseName;
  private Class clazz;

  public DaoGen() {
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

  public Class getClazz() {
    return clazz;
  }

  public void setClazz(Class clazz) {
    this.clazz = clazz;
  }

  @Override
  public String toString() {
    return "DaoGen{" +
        "packageName='" + packageName + '\'' +
        ", databaseName='" + databaseName + '\'' +
        ", clazz=" + clazz +
        '}';
  }
}
