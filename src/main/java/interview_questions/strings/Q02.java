package interview_questions.strings;

import interview_questions.Question;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q02 extends Question {

  private String LOG_MESSAGE = "[%s] & [%s] anagrams?: %s";

  private Q02() {
    super("Question 02:",
        "Write a java program to check if two Strings are anagram in java?");
  }

  public static void main(String[] args) {
    Q02 solution = new Q02();
    solution.run();
  }

  @Override
  public void solution() {
    String anagramPairOne = "Brandon";
    String anagramPairTwo = "nanodrB";
    String nonAnagramOne = "notAnAnagram";
    String nonAnagramTwo = "nopeNotAnAnagramForsure";
    String nonAnagramThree = "nope";
    String nonAnagramFour = "nots";

    System.out.println(String.format(LOG_MESSAGE, anagramPairOne, anagramPairTwo,
        doStringsMatch(anagramPairOne, anagramPairTwo)));

    System.out.println(String.format(LOG_MESSAGE, nonAnagramOne, nonAnagramTwo,
        doStringsMatch(nonAnagramOne, nonAnagramTwo)));

    System.out.println(String.format(LOG_MESSAGE, nonAnagramThree, nonAnagramFour,
        doStringsMatch(nonAnagramThree, nonAnagramFour)));
  }

  private boolean doStringsMatch(String one, String two) {
    String[] oneSplit = one.split("");
    String[] twoSplit = two.split("");
    Arrays.sort(oneSplit);
    Arrays.sort(twoSplit);

    return Arrays.equals(oneSplit, twoSplit);
  }
}
