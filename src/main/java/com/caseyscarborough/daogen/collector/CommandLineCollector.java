package com.caseyscarborough.daogen.collector;

import com.caseyscarborough.daogen.DaoGenClass;
import com.caseyscarborough.daogen.DaoGenField;
import com.caseyscarborough.daogen.DaoGen;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineCollector implements Collector {
  
  private PrintStream out;
  
  public CommandLineCollector() {
    this.out = System.out;
  }

  @Override
  public DaoGen collect() throws Exception {
    DaoGen daoGen = new DaoGen();
    Scanner s = new Scanner(System.in);
    String input;

    out.print("Enter your base package name: ");
    daoGen.setPackageName(s.nextLine().trim());

    out.print("Enter your database name: ");
    daoGen.setDatabaseName(s.nextLine().trim());

    out.println();
    out.println("CLASS INFORMATION:");

    DaoGenClass clazz = new DaoGenClass();
    out.print("Enter the name of the class you are creating a DAO for: ");
    String className = s.nextLine().trim();
    className = className.substring(0, 1).toUpperCase() + className.substring(1);
    clazz.setClassName(className);

    out.print("Enter the name of the database table that maps to the '" + clazz.getClassName() + "' class: ");
    clazz.setTableName(s.nextLine().trim());

    out.println();
    out.println("FIELD INFORMATION:");
    int i = 1;
    do {
      input = "";
      DaoGenField daoGenField = new DaoGenField();

      out.print("Enter the name of field #" + i + " for the '" + clazz.getClassName() + "' class: ");
      daoGenField.setFieldName(s.nextLine().trim());

      out.print("Enter the type of the '" + daoGenField.getFieldName() + "' field. (i.e. Long, String, Integer): ");
      daoGenField.setType(s.nextLine().trim());

      out.print("Enter the database column name that maps to the '" + daoGenField.getFieldName() + "' field: ");
      daoGenField.setColumnName(s.nextLine().trim());

      if (clazz.getIdColumn() == null) {
        out.print("Is this field the ID field for the '" + clazz.getClassName() + "' class? (y/N): ");
        daoGenField.setIdColumn(s.nextLine().trim().equals("y"));
      }

      out.println("\nYou entered the following field information:");
      out.println("* Field Name: " + daoGenField.getFieldName());
      out.println("* Type: " + daoGenField.getType());
      out.println("* Database Column: " + daoGenField.getColumnName());
      out.println("* ID Column? " + (daoGenField.isIdColumn() ? "Yes" : "No"));

      out.print("\nIs this information correct? (Y/n): ");
      input = s.nextLine().trim();

      if (!input.equalsIgnoreCase("n")) {
        clazz.addToColumns(daoGenField);

        out.print("Do you have more fields to enter? (Y/n): ");
        input = s.nextLine().trim();
        i++;

        if (input.equalsIgnoreCase("n") && clazz.getIdColumn() == null) {
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
}
