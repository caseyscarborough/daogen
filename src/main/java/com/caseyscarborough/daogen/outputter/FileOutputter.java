package com.caseyscarborough.daogen.outputter;

import com.caseyscarborough.daogen.Application;
import com.caseyscarborough.daogen.DaoGen;
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

    Map<String, String> daoTemplateMap = new HashMap<String, String>();
    daoTemplateMap.put("className", daoGen.getClassName());
    daoTemplateMap.put("columnsList", daoGen.getColumnsList());
    daoTemplateMap.put("tableName", daoGen.getTableName());
    daoTemplateMap.put("bindValuesList", daoGen.getBindValuesList());
    daoTemplateMap.put("variableName", daoGen.getLowercaseClassName());
    daoTemplateMap.put("idColumn", daoGen.getIdColumnString());
    daoTemplateMap.put("idClass", daoGen.getIdColumnClass());
    daoTemplateMap.put("idResultSetClass", daoGen.getIdColumn().getResultSetType());
    daoTemplateMap.put("setters", daoGen.getResultSetSetters());
    daoTemplateMap.put("bindSetters", daoGen.getBindSetters());
    daoTemplateMap.put("updateSetters", daoGen.getUpdateSetters());
    daoTemplateMap.put("bindUpdateSetters", daoGen.getBindUpdateSetters());
    daoTemplateMap.put("packageName", daoGen.getPackageName());

    System.out.print("Generating the DAO for this class...");
    PrintWriter daoWriter = new PrintWriter("Jdbc" + daoGen.getClassName() + "Dao.java");
    daoTemplate.process(daoTemplateMap, daoWriter);

    daoTemplate = configuration.getTemplate("DaoTemplate.ftl");
    daoWriter = new PrintWriter(daoGen.getClassName() + "Dao.java");
    daoTemplate.process(daoTemplateMap, daoWriter);
    daoWriter.close();
    System.out.println("Done!");

    System.out.print("Would you also like to generate the Model for this class? (y/n): ");
    input = s.nextLine();
    if (input.equalsIgnoreCase("y")) {
      Map<String, String> voTemplateMap = new HashMap<String, String>();
      voTemplateMap.put("className", daoGen.getClassName());
      voTemplateMap.put("fieldDeclarations", daoGen.getFieldDeclarations());
      voTemplateMap.put("fieldList", daoGen.getFieldList());
      voTemplateMap.put("fieldConstructorSetters", daoGen.getFieldConstructorSetters());
      voTemplateMap.put("fieldGettersAndSetters", daoGen.getFieldGettersAndSetters());
      voTemplateMap.put("toString", daoGen.getToString());
      voTemplateMap.put("packageName", daoGen.getPackageName());

      System.out.print("Generating the Model for this class...");
      PrintWriter voWriter = new PrintWriter(daoGen.getClassName() + ".java");
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
