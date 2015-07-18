package com.caseyscarborough.daogen;

import com.caseyscarborough.daogen.generator.DaoGenerator;
import com.caseyscarborough.daogen.generator.DaoGeneratorImpl;

public class Application {

  private static void printBanner() {
    System.out.println("▓█████▄  ▄▄▄       ▒█████    ▄████ ▓█████  ███▄    █ ");
    System.out.println("▒██▀ ██▌▒████▄    ▒██▒  ██▒ ██▒ ▀█▒▓█   ▀  ██ ▀█   █ ");
    System.out.println("░██   █▌▒██  ▀█▄  ▒██░  ██▒▒██░▄▄▄░▒███   ▓██  ▀█ ██▒");
    System.out.println("░▓█▄   ▌░██▄▄▄▄██ ▒██   ██░░▓█  ██▓▒▓█  ▄ ▓██▒  ▐▌██▒");
    System.out.println("░▒████▓  ▓█   ▓██▒░ ████▓▒░░▒▓███▀▒░▒████▒▒██░   ▓██░");
    System.out.println(" ▒▒▓  ▒  ▒▒   ▓▒█░░ ▒░▒░▒░  ░▒   ▒ ░░ ▒░ ░░ ▒░   ▒ ▒ ");
    System.out.println(" ░ ▒  ▒   ▒   ▒▒ ░  ░ ▒ ▒░   ░   ░  ░ ░  ░░ ░░   ░ ▒░");
    System.out.println(" ░ ░  ░   ░   ▒   ░ ░ ░ ▒  ░ ░   ░    ░      ░   ░ ░ ");
    System.out.println("   ░          ░  ░    ░ ░        ░    ░  ░         ░ ");
    System.out.println(" ░                                                   ");
    System.out.println();
    System.out.println("This command line application will generate a class and");
    System.out.println("its DAO class. Your class is required to have an ID field.");
    System.out.println("Use at your own risk!");
    System.out.println();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignored) {
    }
  }

  public static void main(String[] args) throws Exception {
    printBanner();
    DaoGenerator generator = new DaoGeneratorImpl();
    generator.generate();
  }
}
