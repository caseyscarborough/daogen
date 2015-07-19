package com.caseyscarborough.daogen.collector;

import com.caseyscarborough.daogen.Class;
import com.caseyscarborough.daogen.DaoGen;
import com.caseyscarborough.daogen.Field;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineCollector implements Collector {

  private static final String YES_SELECTOR = "y";
  private static final String NO_SELECTOR = "n";
  private static final String SELECTION_STRING = "(" + YES_SELECTOR + "/" + NO_SELECTOR + ")";

  private final PrintStream out;

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

    Class clazz = new Class();

    String className = getInput(s, "Enter the name of the class you are creating a DAO for: ");
    className = className.substring(0, 1).toUpperCase() + className.substring(1);
    clazz.setName(className);

    String tableName = getInput(s, "Enter the name of the database table that maps to the '" + clazz.getName() + "' class: ");
    clazz.setTableName(tableName);

    out.println();
    out.println("FIELD INFORMATION:");
    int i = 1;
    do {
      Field field = new Field(clazz);

      String fieldName = getInput(s, "Enter the name of field #" + i + " for the '" + clazz.getName() + "' class: ");
      field.setName(fieldName);

      String fieldType = getInput(s, "Enter the type of the '" + field.getName() + "' field. (i.e. Long, String, Integer): ");
      field.setType(fieldType);

      String columnName = getInput(s, "Enter the database column name that maps to the '" + field.getName() + "' field: ");
      field.setColumnName(columnName);

      if (clazz.getIdField() == null) {
        input = getInput(s, "Is this field the ID field for the '" + clazz.getName() + "' class? " + SELECTION_STRING + " ");
        field.setIdField(input.equals("y"));
      }

      out.println("\nYou entered the following field information:");
      out.println("* Field Name: " + field.getName());
      out.println("* Type: " + field.getType());
      out.println("* Database Column: " + field.getColumnName());
      out.println("* ID Column? " + (field.isIdField() ? "Yes" : "No"));
      out.println();

      input = getInput(s, "Is this information correct? " + SELECTION_STRING + ": ");
      if (!input.equalsIgnoreCase("n")) {
        clazz.addToColumns(field);
        input = getInput(s, "Do you have more fields to enter? " + SELECTION_STRING + ": ");
        i++;

        if (input.equalsIgnoreCase("n") && clazz.getIdField() == null) {
          out.println("You have not yet entered an ID column. The Dao Generator currently requires an ID column for your class.");
          input = "y";
        }
      } else {
        out.println("Undoing... Re-enter this field.");
        input = "";
      }
    } while (!input.equalsIgnoreCase("n"));

    daoGen.setClazz(clazz);
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
