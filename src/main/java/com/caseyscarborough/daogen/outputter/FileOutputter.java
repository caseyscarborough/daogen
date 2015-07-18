package com.caseyscarborough.daogen.outputter;

import com.caseyscarborough.daogen.Application;
import com.caseyscarborough.daogen.DaoGen;
import com.caseyscarborough.daogen.DaoGenClass;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileOutputter implements Outputter {

  @Override
  public void output(DaoGen daoGen) throws Exception {
    String input = "";
    Scanner s = new Scanner(System.in);

    Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
    configuration.setClassForTemplateLoading(Application.class, "/");
    configuration.setDefaultEncoding("UTF-8");

    Template daoTemplate = configuration.getTemplate("DaoImplTemplate.ftl");

    DaoGenClass clazz = daoGen.getClazz();
    Map<String, String> daoTemplateMap = new HashMap<String, String>();
    daoTemplateMap.put("className", clazz.getClassName());
    daoTemplateMap.put("columnsList", clazz.getColumnsList());
    daoTemplateMap.put("tableName", clazz.getTableName());
    daoTemplateMap.put("bindValuesList", clazz.getBindValuesList());
    daoTemplateMap.put("variableName", clazz.getVariableName());
    daoTemplateMap.put("idColumn", clazz.getIdColumnString());
    daoTemplateMap.put("idClass", clazz.getIdColumnClass());
    daoTemplateMap.put("idResultSetClass", clazz.getIdColumn().getResultSetType());
    daoTemplateMap.put("setters", clazz.getResultSetSetters());
    daoTemplateMap.put("bindSetters", clazz.getBindSetters());
    daoTemplateMap.put("updateSetters", clazz.getUpdateSetters());
    daoTemplateMap.put("bindUpdateSetters", clazz.getBindUpdateSetters());
    daoTemplateMap.put("packageName", daoGen.getPackageName());

    System.out.print("Generating the DAO for this class...");
    PrintWriter daoWriter = new PrintWriter("Jdbc" + clazz.getClassName() + "Dao.java");
    daoTemplate.process(daoTemplateMap, daoWriter);

    daoTemplate = configuration.getTemplate("DaoTemplate.ftl");
    daoWriter = new PrintWriter(clazz.getClassName() + "Dao.java");
    daoTemplate.process(daoTemplateMap, daoWriter);
    daoWriter.close();
    System.out.println("Done!");

    System.out.print("Would you also like to generate the Model for this class? (y/n): ");
    input = s.nextLine();
    if (input.equalsIgnoreCase("y")) {
      Map<String, String> voTemplateMap = new HashMap<String, String>();
      voTemplateMap.put("className", clazz.getClassName());
      voTemplateMap.put("fieldDeclarations", clazz.getFieldDeclarations());
      voTemplateMap.put("fieldList", clazz.getFieldList());
      voTemplateMap.put("fieldConstructorSetters", clazz.getFieldConstructorSetters());
      voTemplateMap.put("fieldGettersAndSetters", clazz.getFieldGettersAndSetters());
      voTemplateMap.put("toString", clazz.getToString());
      voTemplateMap.put("packageName", daoGen.getPackageName());

      System.out.print("Generating the Model for this class...");
      PrintWriter voWriter = new PrintWriter(clazz.getClassName() + ".java");
      Template voTemplate = configuration.getTemplate("ModelTemplate.ftl");
      voTemplate.process(voTemplateMap, voWriter);
      voWriter.close();
      System.out.println("Done!");
    }

    System.out.print("Do you need to output the Dao superclass (select yes if you have not yet done so)? (y/n): ");
    input = s.nextLine().trim();

    if (input.equalsIgnoreCase("y")) {
      System.out.print("Generating BaseDao class...");
      PrintWriter writer = new PrintWriter("BaseDao.java");
      Map<String, String> daoBaseTemplateMap = new HashMap<String, String>();
      daoBaseTemplateMap.put("packageName", daoGen.getPackageName());
      configuration.getTemplate("BaseDaoTemplate.ftl").process(daoBaseTemplateMap, writer);
      writer.close();
      System.out.println("Done!");
    }
  }
}
