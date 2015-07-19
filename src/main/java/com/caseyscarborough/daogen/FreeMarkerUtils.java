package com.caseyscarborough.daogen;

import java.util.HashMap;
import java.util.Map;

public class FreeMarkerUtils {

  public static Map<String, String> getMapForDaoGen(DaoGen daoGen) {
    DaoGenClass daoGenClass = daoGen.getClazz();

    Map<String, String> map = new HashMap<String, String>();
    map.put("className", daoGenClass.getClassName());
    map.put("columnsList", daoGenClass.getColumnsList());
    map.put("tableName", daoGenClass.getTableName());
    map.put("bindValuesList", daoGenClass.getBindValuesList());
    map.put("variableName", daoGenClass.getVariableName());
    map.put("idColumn", daoGenClass.getIdColumnString());
    map.put("idClass", daoGenClass.getIdColumnClass());
    map.put("idResultSetClass", daoGenClass.getIdColumn().getResultSetType());
    map.put("setters", daoGenClass.getResultSetSetters());
    map.put("bindSetters", daoGenClass.getBindSetters());
    map.put("updateSetters", daoGenClass.getUpdateSetters());
    map.put("bindUpdateSetters", daoGenClass.getBindUpdateSetters());
    map.put("packageName", daoGen.getPackageName());
    map.put("fieldDeclarations", daoGenClass.getFieldDeclarations());
    map.put("fieldList", daoGenClass.getFieldList());
    map.put("fieldConstructorSetters", daoGenClass.getFieldConstructorSetters());
    map.put("fieldGettersAndSetters", daoGenClass.getFieldGettersAndSetters());
    map.put("toString", daoGenClass.getToString());
    return map;
  }
}
