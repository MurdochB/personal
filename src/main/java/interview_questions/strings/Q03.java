package interview_questions.strings;

import interview_questions.Question;
import java.util.Arrays;

public class Q03 extends Question {

  private String LOG_MESSAGE = "[%s] %s";

  private Q03() {
    super("Question 03:",
        "Write a program to check if String has all unique characters in java?");
  }

  public static void main(String[] args) {
    Q03 solution = new Q03();
    solution.run();
  }

  @Override
  public void solution() {
    String allUniqueChars = "abcdefghijklmno";
    String nonUnique = "aabbccddee";
    String nonUniqueTwo = "abcdefghijklmnopqrstuvwxyzz";

    System.out.println(String.format(LOG_MESSAGE, allUniqueChars, isUnique(allUniqueChars)));
    System.out.println(String.format(LOG_MESSAGE, nonUnique, isUnique(nonUnique)));
    System.out.println(String.format(LOG_MESSAGE, nonUniqueTwo, isUnique(nonUniqueTwo)));

  }

  private boolean isUnique(String input){
    long numDistinctChars = Arrays.stream(input.split("")).distinct().count();
    return input.length() == numDistinctChars;
  }
}
