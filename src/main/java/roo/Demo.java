package roo;

public class Demo {

  public static void main(String[] args) {
    // Demo application to create a new cron object & print a table:

    Cron cron = new Cron(args);
    cron.printTable();
  }
}
