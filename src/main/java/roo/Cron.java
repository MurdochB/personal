package roo;

public class Cron {

  private String minute;
  private String hour;
  private String dayOfMonth;
  private String month;
  private String dayOfWeek;
  private String command;

  public Cron(String[] args) {
    // TODO - validate input
    minute = args[0];
    hour = args[1];
    dayOfMonth = args[2];
    month = args[3];
    dayOfWeek = args[4];
    command = args[5];
  }

  public String getMinutes() {
    // Call a method to work out what to render
    return "mins";
  }

  public String getHours() {
    // Call a method to work out what to render
    return "hours";
  }

  public String getDayOfMonths() {
    // Call a method to work out what to render
    return "day of month";
  }

  public String getMonths() {
    // Call a method to work out what to render
    return "month";
  }

  public String getDaysOfWeek() {
    // Call a method to work out what to render
    return "day of week";
  }

  public String getCommand() {
    return "command here";
  }

  public void printTable() {
    // TODO - change this into a nice string formatter
    System.out.println("minute        " + getMinutes());
    System.out.println("hour          " + getHours());
    System.out.println("day of month  " + getDayOfMonths());
    System.out.println("month         " + getMonths());
    System.out.println("command       " + getCommand());
  }
}
