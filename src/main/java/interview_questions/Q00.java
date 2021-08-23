package interview_questions;

public class Q00 extends Question {

  private Q00() {
    super("Question 00:",
        "How to do this?");
  }

  public static void main(String[] args) {
    Q00 solution = new Q00();
    solution.run();
  }

  @Override
  public void solution() {
    System.out.println("i am Q00 soultion");
  }
}
