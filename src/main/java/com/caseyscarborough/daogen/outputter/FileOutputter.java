package com.caseyscarborough.daogen.outputter;

import com.caseyscarborough.daogen.Application;
import com.caseyscarborough.daogen.Class;
import com.caseyscarborough.daogen.Constants;
import com.caseyscarborough.daogen.DaoGen;
import freemarker.template.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileOutputter implements Outputter {

  private static final String OUTPUT_DIRECTORY = "output";

  private final Configuration configuration;

  public FileOutputter() {
    configuration = new Configuration(Configuration.VERSION_2_3_23);
    configuration.setClassForTemplateLoading(Application.class, "/");
    configuration.setDefaultEncoding("UTF-8");
  }

  @Override
  public void output(DaoGen daoGen) throws Exception {
    Scanner s = new Scanner(System.in);

    Map<String, Object> templateMap = new HashMap<String, Object>();
    templateMap.put("daoGen", daoGen);

    DefaultObjectWrapper wrapper = new DefaultObjectWrapper(Configuration.VERSION_2_3_23);
    TemplateHashModel staticModels = wrapper.getStaticModels();
    TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get("com.caseyscarborough.daogen.Constants");
    templateMap.put("Constants", fileStatics);
    Class clazz = daoGen.getClazz();

    new File(OUTPUT_DIRECTORY + "/" + Constants.DAO_PACKAGE_NAME).mkdirs();
    System.out.print("Generating the DAO classes...");
    writeTemplate(OUTPUT_DIRECTORY + "/" + Constants.DAO_PACKAGE_NAME + "/" + clazz.getName() + Constants.DAO_CLASS_SUFFIX + ".java", "templates/DaoTemplate.ftl", templateMap);
    writeTemplate(OUTPUT_DIRECTORY + "/" + Constants.DAO_PACKAGE_NAME + "/Jdbc" + clazz.getName() + Constants.DAO_CLASS_SUFFIX + ".java", "templates/DaoImplTemplate.ftl", templateMap);
    System.out.println("Done!");

    String input = getInput(s, "Do you need to output the Dao superclass (select yes if you have not yet done so)? (y/n): ");
    if (input.equalsIgnoreCase("y")) {
      System.out.print("Generating BaseDao class...");
      writeTemplate(OUTPUT_DIRECTORY + "/" + Constants.DAO_PACKAGE_NAME + "/Base" + Constants.DAO_CLASS_SUFFIX + ".java", "templates/BaseDaoTemplate.ftl", templateMap);
      System.out.println("Done!");
    }

    input = getInput(s, "Would you also like to generate the Model for this class? (y/n): ");
    if (input.equalsIgnoreCase("y")) {
      new File(OUTPUT_DIRECTORY + "/" + Constants.MODEL_PACKAGE_NAME).mkdir();
      System.out.print("Generating the Model for this class...");
      writeTemplate(OUTPUT_DIRECTORY + "/" + Constants.MODEL_PACKAGE_NAME + "/" + clazz.getName() + ".java", "templates/ModelTemplate.ftl", templateMap);
      System.out.println("Done!");
    }

    input = getInput(s, "Would you also like to generate the Service layer for this class? (y/n):");
    if (input.equalsIgnoreCase("y")) {
      new File(OUTPUT_DIRECTORY + "/" + Constants.SERVICE_PACKAGE_NAME).mkdir();
      System.out.print("Generating Service classes...");
      writeTemplate(OUTPUT_DIRECTORY + "/" + Constants.SERVICE_PACKAGE_NAME + "/" + clazz.getName() + "Service.java", "templates/ServiceTemplate.ftl", templateMap);
      writeTemplate(OUTPUT_DIRECTORY + "/" + Constants.SERVICE_PACKAGE_NAME + "/" + clazz.getName() + "ServiceImpl.java", "templates/ServiceImplTemplate.ftl", templateMap);
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

  private void writeTemplate(String filename, String templateString, Map<String, Object> parameters) throws IOException, TemplateException {
    PrintWriter writer = new PrintWriter(filename);
    Template template = configuration.getTemplate(templateString);
    template.process(parameters, writer);
    writer.close();
  }
}
