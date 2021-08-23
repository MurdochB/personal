package interview_questions.strings;

import interview_questions.Question;

public class Q01 extends Question {

  private Q01() {
    super("Question 01:",
        "How to reverse a String in java? Can you write a program without using any java inbuilt methods?");
  }

  public static void main(String[] args) {
    Q01 solution = new Q01();
    solution.run();
  }

  @Override
  public void solution() {
    String startingString = "Hello I am the starting string";
    StringBuilder reversedString = new StringBuilder();

    for(int i = startingString.length() - 1; i >= 0; i--){
      reversedString.append(startingString.charAt(i));
    }

    System.out.println("Starting string: " + startingString);
    System.out.println("Reversed string: " + reversedString);
  }
}
