package com.caseyscarborough.daogen.outputter;

import com.caseyscarborough.daogen.Application;
import com.caseyscarborough.daogen.DaoGen;
import com.caseyscarborough.daogen.DaoGenClass;
import com.caseyscarborough.daogen.FreeMarkerUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class FileOutputter implements Outputter {

  private final Configuration configuration;

  public FileOutputter() {
    configuration = new Configuration(Configuration.VERSION_2_3_23);
    configuration.setClassForTemplateLoading(Application.class, "/");
    configuration.setDefaultEncoding("UTF-8");
  }

  @Override
  public void output(DaoGen daoGen) throws Exception {
    Scanner s = new Scanner(System.in);

    DaoGenClass clazz = daoGen.getClazz();
    Map<String, String> templateMap = FreeMarkerUtils.getMapForDaoGen(daoGen);

    System.out.print("Generating the DAO for this class...");
    writeTemplate(clazz.getClassName() + "Dao.java", "templates/DaoTemplate.ftl", templateMap);
    writeTemplate("Jdbc" + clazz.getClassName() + "Dao.java", "templates/DaoImplTemplate.ftl", templateMap);
    System.out.println("Done!");

    String input = getInput(s, "Would you also like to generate the Model for this class? (y/n): ");
    if (input.equalsIgnoreCase("y")) {
      System.out.print("Generating the Model for this class...");
      writeTemplate(clazz.getClassName() + ".java", "templates/ModelTemplate.ftl", templateMap);
      System.out.println("Done!");
    }

    input = getInput(s, "Do you need to output the Dao superclass (select yes if you have not yet done so)? (y/n): ");
    if (input.equalsIgnoreCase("y")) {
      System.out.print("Generating BaseDao class...");
      writeTemplate("BaseDao.java", "templates/BaseDaoTemplate.ftl", templateMap);
      System.out.println("Done!");
    }
  }

  private String getInput(Scanner s, String message) {
    String input = "";
    while (input.isEmpty()) {
      System.out.print(message);
      input = s.nextLine().trim();
    }
    return input;
  }

  private void writeTemplate(String filename, String templateString, Map<String, String> parameters) throws IOException, TemplateException {
    PrintWriter writer = new PrintWriter(filename);
    Template template = configuration.getTemplate(templateString);
    template.process(parameters, writer);
    writer.close();
  }
}
