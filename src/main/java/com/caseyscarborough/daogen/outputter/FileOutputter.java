package com.caseyscarborough.daogen.outputter;

import com.caseyscarborough.daogen.Application;
import com.caseyscarborough.daogen.DaoGen;
import com.caseyscarborough.daogen.DaoGenClass;
import com.caseyscarborough.daogen.util.FreeMarkerUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class FileOutputter implements Outputter {

  private static final String OUTPUT_DIRECTORY = "output";
  private static final String DAO_DIRECTORY = "dao";
  private static final String MODEL_DIRECTORY = "vo";
  private static final String SERVICE_DIRECTORY = "service";

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

    new File(OUTPUT_DIRECTORY + "/" + DAO_DIRECTORY).mkdirs();
    System.out.print("Generating the DAO classes...");
    writeTemplate(OUTPUT_DIRECTORY + "/" + DAO_DIRECTORY + "/" + clazz.getClassName() + "Dao.java", "templates/DaoTemplate.ftl", templateMap);
    writeTemplate(OUTPUT_DIRECTORY + "/" + DAO_DIRECTORY + "/Jdbc" + clazz.getClassName() + "Dao.java", "templates/DaoImplTemplate.ftl", templateMap);
    System.out.println("Done!");

    String input = getInput(s, "Do you need to output the Dao superclass (select yes if you have not yet done so)? (y/n): ");
    if (input.equalsIgnoreCase("y")) {
      System.out.print("Generating BaseDao class...");
      writeTemplate(OUTPUT_DIRECTORY + "/" + DAO_DIRECTORY + "/BaseDao.java", "templates/BaseDaoTemplate.ftl", templateMap);
      System.out.println("Done!");
    }

    input = getInput(s, "Would you also like to generate the Model for this class? (y/n): ");
    if (input.equalsIgnoreCase("y")) {
      new File(OUTPUT_DIRECTORY + "/" + MODEL_DIRECTORY).mkdir();
      System.out.print("Generating the Model for this class...");
      writeTemplate(OUTPUT_DIRECTORY + "/" + MODEL_DIRECTORY + "/" + clazz.getClassName() + ".java", "templates/ModelTemplate.ftl", templateMap);
      System.out.println("Done!");
    }

    input = getInput(s, "Would you also like to generate the Service layer for this class? (y/n):");
    if (input.equalsIgnoreCase("y")) {
      new File(OUTPUT_DIRECTORY + "/" + SERVICE_DIRECTORY).mkdir();
      System.out.print("Generating Service classes...");
      writeTemplate(OUTPUT_DIRECTORY + "/" + SERVICE_DIRECTORY + "/" + clazz.getClassName() + "Service.java", "templates/ServiceTemplate.ftl", templateMap);
      writeTemplate(OUTPUT_DIRECTORY + "/" + SERVICE_DIRECTORY + "/" + clazz.getClassName() + "ServiceImpl.java", "templates/ServiceImplTemplate.ftl", templateMap);
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
