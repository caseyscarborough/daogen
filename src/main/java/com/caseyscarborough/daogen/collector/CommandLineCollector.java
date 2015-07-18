package com.caseyscarborough.daogen.collector;

import com.caseyscarborough.daogen.DaoColumn;
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
    out.print("Enter the name of the class you are creating a DAO for: ");
    String className = s.nextLine().trim();
    className = className.substring(0, 1).toUpperCase() + className.substring(1);
    daoGen.setClassName(className);

    out.print("Enter the name of the database table that maps to the '" + daoGen.getClassName() + "' class: ");
    daoGen.setTableName(s.nextLine().trim());

    out.println();
    out.println("FIELD INFORMATION:");
    int i = 1;
    do {
      input = "";
      DaoColumn daoColumn = new DaoColumn();

      out.print("Enter the name of field #" + i + " for the '" + daoGen.getClassName() + "' class: ");
      daoColumn.setFieldName(s.nextLine().trim());

      out.print("Enter the type of the '" + daoColumn.getFieldName() + "' field. (i.e. Long, String, Integer): ");
      daoColumn.setType(s.nextLine().trim());

      out.print("Enter the database column name that maps to the '" + daoColumn.getFieldName() + "' field: ");
      daoColumn.setColumnName(s.nextLine().trim());

      if (daoGen.getIdColumn() == null) {
        out.print("Is this field the ID field for the '" + daoGen.getClassName() + "' class? (y/N): ");
        daoColumn.setIdColumn(s.nextLine().trim().equals("y"));
      }

      out.println("\nYou entered the following field information:");
      out.println("* Field Name: " + daoColumn.getFieldName());
      out.println("* Type: " + daoColumn.getType());
      out.println("* Database Column: " + daoColumn.getColumnName());
      out.println("* ID Column? " + (daoColumn.isIdColumn() ? "Yes" : "No"));

      out.print("\nIs this information correct? (Y/n): ");
      input = s.nextLine().trim();

      if (!input.equalsIgnoreCase("n")) {
        daoGen.addToColumns(daoColumn);

        out.print("Do you have more fields to enter? (Y/n): ");
        input = s.nextLine().trim();
        i++;

        if (input.equalsIgnoreCase("n") && daoGen.getIdColumn() == null) {
          out.println("You have not yet entered an ID column. The Dao Generator currently requires an ID column for your class.");
          input = "y";
        }
      } else {
        out.println("Undoing... Re-enter this field.");
        input = "";
      }
    } while (!input.equalsIgnoreCase("n"));
    return daoGen;
  }
}
