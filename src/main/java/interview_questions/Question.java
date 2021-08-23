package interview_questions;

public abstract class Question {

  private String name;
  private String problem;

  public Question(String name, String problem) {
    this.name = name;
    this.problem = problem;
  }

  public void run() {
    System.out.println(name);
    System.out.println(problem);
    System.out.println();
    System.out.println();
    solution();
  }

  public abstract void solution();
}
