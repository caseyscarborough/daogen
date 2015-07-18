package com.caseyscarborough.daogen;

public class DaoGen {

  private String packageName;
  private String databaseName;
  private DaoGenClass clazz;

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

  public DaoGenClass getClazz() {
    return clazz;
  }

  public void setClazz(DaoGenClass clazz) {
    this.clazz = clazz;
  }
}
