package roo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cron {

  private static final int MINUTE_MIN = 0;
  private static final int MINUTE_MAX = 59;
  private static final int HOUR_MIN = 0;
  private static final int HOUR_MAX = 23;
  private static final int DAY_OF_MONTH_MIN = 1;
  private static final int DAY_OF_MONTH_MAX = 31;
  private static final int MONTH_MIN = 1;
  private static final int MONTH_MAX = 12;
  private static final int DAY_OF_WEEK_MIN = 1;
  private static final int DAY_OF_WEEK_MAX = 7;

  private static final String SINGLE_DIGIT = "[0-9]+";
  private static final String COMMA_SEP_DIGITS = "[0-9]+(,[0-9]+)+";
  private static final String RANGE_DIGITS = "[0-9]+-[0-9]+";
  private static final String SLASHED_DIGITS = "([0-9]+|\\*)/[0-9]+";

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

  private String parseInput(String input, int min, int max) {
    List<Integer> values = new ArrayList<>();

    // Update this to an enum of types of input
    if (input.equals("*")) {
      for (int i = min; i <= max; i++) {
        values.add(i);
      }
    } else if (input.matches(SINGLE_DIGIT)) {
      values.add(Integer.parseInt(input));
    } else if (input.matches(COMMA_SEP_DIGITS)) {
      String[] split = input.split(",");
      for (String s : split) {
        values.add(Integer.parseInt(s));
      }
    } else if (input.matches(RANGE_DIGITS)) {
      String[] rangeVals = input.split("-");
      for (int i = Integer.parseInt(rangeVals[0]); i <= Integer.parseInt(rangeVals[1]); i++) {
        values.add(i);
      }
    } else if (input.matches(SLASHED_DIGITS)) {
      String[] slashVals = input.split("/");
      if(slashVals[0].equals("*")){
        slashVals[0] = String.valueOf(min);
      }
      for (int i = Integer.parseInt(slashVals[0]); i <= max; i+= Integer.parseInt(slashVals[1])) {
        values.add(i);
      }
    }

    return values.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(" "));
  }

  public String getMinutes() {
    return parseInput(minute, MINUTE_MIN, MINUTE_MAX);
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
