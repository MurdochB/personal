package roo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cron {

  private static final String TABLE_FORMAT = "%-14s%s";

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

  private static final String ALL_DIGITS = "\\*";
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
    if (!validateArguments(args)) {
      throw new IllegalStateException(
          "Invalid cron input, cannot be parsed into a cron job. " + Arrays.toString(args));
    }

    minute = args[0];
    hour = args[1];
    dayOfMonth = args[2];
    month = args[3];
    dayOfWeek = args[4];
    command = args[5];
  }

  public String getMinutes() {
    return parseInput(minute, MINUTE_MIN, MINUTE_MAX);
  }

  public String getHours() {
    return parseInput(hour, HOUR_MIN, HOUR_MAX);
  }

  public String getDayOfMonths() {
    return parseInput(dayOfMonth, DAY_OF_MONTH_MIN, DAY_OF_MONTH_MAX);
  }

  public String getMonths() {
    return parseInput(month, MONTH_MIN, MONTH_MAX);
  }

  public String getDaysOfWeek() {
    return parseInput(dayOfWeek, DAY_OF_WEEK_MIN, DAY_OF_WEEK_MAX);
  }

  public String getCommand() {
    return command;
  }

  public void printTable() {
    System.out.println(String.format(TABLE_FORMAT, "minute", getMinutes()));
    System.out.println(String.format(TABLE_FORMAT, "hour", getHours()));
    System.out.println(String.format(TABLE_FORMAT, "day of month", getDayOfMonths()));
    System.out.println(String.format(TABLE_FORMAT, "month", getMonths()));
    System.out.println(String.format(TABLE_FORMAT, "day of week", getDaysOfWeek()));
    System.out.println(String.format(TABLE_FORMAT, "command", getCommand()));
  }

  private boolean validateArguments(String[] args) {
    if (args != null && args.length >= 6) {
      return validateArgument(args[0], MINUTE_MIN, MINUTE_MAX) &&
          validateArgument(args[1], HOUR_MIN, HOUR_MAX) &&
          validateArgument(args[2], DAY_OF_MONTH_MIN, DAY_OF_MONTH_MAX) &&
          validateArgument(args[3], MONTH_MIN, MONTH_MAX) &&
          validateArgument(args[4], DAY_OF_WEEK_MIN, DAY_OF_WEEK_MAX);
    }
    return false;
  }

  private boolean validateArgument(String arg, int min, int max) {
    if (arg.matches(ALL_DIGITS)) {
      return true;
    } else if (arg.matches(SINGLE_DIGIT)) {
      int val = Integer.parseInt(arg);
      return val >= min && val <= max;
    } else if (arg.matches(COMMA_SEP_DIGITS)) {
      return valuesAreInMinMaxRange(getCommaSepValues(arg), min, max);
    } else if (arg.matches(RANGE_DIGITS)) {
      return valuesAreInMinMaxRange(getRangeValues(arg), min, max);
    } else if (arg.matches(SLASHED_DIGITS)) {
      return valuesAreInMinMaxRange(getSlashedValues(arg, min, max), min, max);
    }
    return false;
  }

  private boolean valuesAreInMinMaxRange(List<Integer> values, int min, int max) {
    return Collections.min(values) >= min && Collections.max(values) <= max;
  }

  private String parseInput(String input, int min, int max) {
    List<Integer> values = new ArrayList<>();

    if (input.matches(ALL_DIGITS)) {
      values.addAll(getAllValues(min, max));
    } else if (input.matches(SINGLE_DIGIT)) {
      values.add(Integer.parseInt(input));
    } else if (input.matches(COMMA_SEP_DIGITS)) {
      values.addAll(getCommaSepValues(input));
    } else if (input.matches(RANGE_DIGITS)) {
      values.addAll(getRangeValues(input));
    } else if (input.matches(SLASHED_DIGITS)) {
      values.addAll(getSlashedValues(input, min, max));
    }
    return values.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(" "));
  }

  private List<Integer> getAllValues(int min, int max) {
    List<Integer> values = new ArrayList<>();
    for (int i = min; i <= max; i++) {
      values.add(i);
    }
    return values;
  }

  private List<Integer> getCommaSepValues(String input) {
    return Arrays.stream(input.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  private List<Integer> getRangeValues(String input) {
    List<Integer> values = new ArrayList<>();
    String[] rangeVals = input.split("-");
    for (int i = Integer.parseInt(rangeVals[0]); i <= Integer.parseInt(rangeVals[1]); i++) {
      values.add(i);
    }
    return values;
  }

  private List<Integer> getSlashedValues(String input, int min, int max) {
    List<Integer> values = new ArrayList<>();
    String[] slashVals = input.split("/");
    if (slashVals[0].equals("*")) {
      // Starting with a * in a slashed value is the same as starting at the minimum
      slashVals[0] = String.valueOf(min);
    }
    for (int i = Integer.parseInt(slashVals[0]); i <= max; i += Integer.parseInt(slashVals[1])) {
      values.add(i);
    }
    return values;
  }
}
