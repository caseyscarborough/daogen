package com.caseyscarborough.daogen.collector;

import com.caseyscarborough.daogen.DaoGen;
import com.caseyscarborough.daogen.DaoGenClass;
import com.caseyscarborough.daogen.DaoGenField;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineCollector implements Collector {

  private static final String YES_SELECTOR = "y";
  private static final String NO_SELECTOR = "n";
  private static final String SELECTION_STRING = "(" + YES_SELECTOR + "/" + NO_SELECTOR + ")";

  private PrintStream out;

  public CommandLineCollector() {
    this.out = System.out;
  }

  @Override
  public DaoGen collect() throws Exception {
    DaoGen daoGen = new DaoGen();
    Scanner s = new Scanner(System.in);
    String input;

    String packageName = getInput(s, "Enter your base package name: ");
    daoGen.setPackageName(packageName);

    String databaseName = getInput(s, "Enter your database name: ");
    daoGen.setDatabaseName(databaseName);

    out.println();
    out.println("CLASS INFORMATION:");

    DaoGenClass daoGenClass = new DaoGenClass();

    String className = getInput(s, "Enter the name of the class you are creating a DAO for: ");
    className = className.substring(0, 1).toUpperCase() + className.substring(1);
    daoGenClass.setClassName(className);

    String tableName = getInput(s, "Enter the name of the database table that maps to the '" + daoGenClass.getClassName() + "' class: ");
    daoGenClass.setTableName(tableName);

    out.println();
    out.println("FIELD INFORMATION:");
    int i = 1;
    do {
      DaoGenField daoGenField = new DaoGenField(daoGenClass);

      String fieldName = getInput(s, "Enter the name of field #" + i + " for the '" + daoGenClass.getClassName() + "' class: ");
      daoGenField.setFieldName(fieldName);

      String fieldType = getInput(s, "Enter the type of the '" + daoGenField.getFieldName() + "' field. (i.e. Long, String, Integer): ");
      daoGenField.setType(fieldType);

      String columnName = getInput(s, "Enter the database column name that maps to the '" + daoGenField.getFieldName() + "' field: ");
      daoGenField.setColumnName(columnName);

      if (daoGenClass.getIdColumn() == null) {
        input = getInput(s, "Is this field the ID field for the '" + daoGenClass.getClassName() + "' class? " + SELECTION_STRING + " ");
        daoGenField.setIdColumn(input.equals("y"));
      }

      out.println("\nYou entered the following field information:");
      out.println("* Field Name: " + daoGenField.getFieldName());
      out.println("* Type: " + daoGenField.getType());
      out.println("* Database Column: " + daoGenField.getColumnName());
      out.println("* ID Column? " + (daoGenField.isIdColumn() ? "Yes" : "No"));
      out.println();

      input = getInput(s, "Is this information correct? " + SELECTION_STRING + ": ");
      if (!input.equalsIgnoreCase("n")) {
        daoGenClass.addToColumns(daoGenField);
        input = getInput(s, "Do you have more fields to enter? " + SELECTION_STRING + ": ");
        i++;

        if (input.equalsIgnoreCase("n") && daoGenClass.getIdColumn() == null) {
          out.println("You have not yet entered an ID column. The Dao Generator currently requires an ID column for your class.");
          input = "y";
        }
      } else {
        out.println("Undoing... Re-enter this field.");
        input = "";
      }
    } while (!input.equalsIgnoreCase("n"));

    daoGen.setClazz(daoGenClass);
    return daoGen;
  }

  private String getInput(Scanner s, String message) {
    String input = "";
    while (input.isEmpty()) {
      out.print(message);
      input = s.nextLine().trim();
    }
    return input;
  }

}
