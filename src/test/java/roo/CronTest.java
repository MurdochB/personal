package roo;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CronTest {

  @Test
  public void testValidArgumentsAreParsed() {
    // Given a set of arguments for a cron are valid
    String[] validArguments = {"1", "1", "1", "1", "1", "cmd"};

    // When a cron is created with the arguments
    Cron cron = new Cron(validArguments);

    // Then the cron should have the expected values
    assertThat("Minutes don't match", cron.getMinutes(), is("1"));
    assertThat("Hours don't match", cron.getHours(), is("1"));
    assertThat("Day of month don't match", cron.getDayOfMonths(), is("1"));
    assertThat("Months don't match", cron.getMonths(), is("1"));
    assertThat("Day of week don't match", cron.getDaysOfWeek(), is("1"));
    assertThat("Command doesn't match", cron.getCommand(), is("cmd"));
  }

  @Test(expected = IllegalStateException.class)
  public void testNullArguments() {
    // Given a null argument is used to create a cron job
    String[] nullArgs = null;

    // When the cron is created
    Cron cron = new Cron(nullArgs);

    // Then a IllegalStateException should be thrown
  }

  @Test(expected = IllegalStateException.class)
  public void testStringArguments() {
    // Given strings are given as arguments to create a cron job
    String[] stringInput = {"cmd", "cmd", "cmd", "cmd", "cmd", "cmd"};

    // When the cron is created
    Cron cron = new Cron(stringInput);

    // An IllegalStateException should be thrown
  }

  @Test(expected = IllegalStateException.class)
  public void testArgumentsAreTooSmall() {
    // Given values that are too small for each field are given as arguments to create a cron job
    String[] tooSmall = {"-1", "-1", "0", "0", "0", ""};

    // When the cron is created
    Cron cron = new Cron(tooSmall);

    // An IllegalStateException should be thrown
  }


  @Test(expected = IllegalStateException.class)
  public void testArgumentsAreTooLarge() {
    // Given values that are too large for each field are given as arguments to create a cron job
    String[] tooLarge = {"60", "24", "32", "13", "8", "cmd"};
    // When the cron is created
    Cron cron = new Cron(tooLarge);

    // An IllegalStateException should be thrown
  }

  @Test(expected = IllegalStateException.class)
  public void testTooFewArguments() {
    // Given too few arguments are given to create a cron job

    String[] tooFew = {"0", "0"};
    // When the cron is created
    Cron cron = new Cron(tooFew);

    // An IllegalStateException should be thrown
  }

  @Test
  public void testTooManyArguments() {
    // Given too few arguments are given to create a cron job
    String[] tooMany = {"1", "1", "1", "1", "1", "cmd", "cmd2"};

    // When the cron is created
    Cron cron = new Cron(tooMany);

    // Then the cron should be built, and the extra argument is ignored
    assertThat("Minutes don't match", cron.getMinutes(), is("1"));
    assertThat("Hours don't match", cron.getHours(), is("1"));
    assertThat("Day of month don't match", cron.getDayOfMonths(), is("1"));
    assertThat("Months don't match", cron.getMonths(), is("1"));
    assertThat("Day of week don't match", cron.getDaysOfWeek(), is("1"));
    assertThat("Command doesn't match", cron.getCommand(), is("cmd"));
  }

  @Test
  public void testMinutes() {
    // Given a cron is set up with values for the minutes:

    String[] star = {"*", "*", "*", "*", "*", "cmd"};
    String[] single = {"12", "*", "*", "*", "*", "cmd"};
    String[] commaSep = {"1,2,55,56", "*", "*", "*", "*", "cmd"};
    String[] range = {"20-25", "*", "*", "*", "*", "cmd"};
    String[] starSlashed = {"*/10", "*", "*", "*", "*", "cmd"};
    String[] nonStarSlashed = {"20/10", "*", "*", "*", "*", "cmd"};

    Cron cronStar = new Cron(star);
    Cron cronSingle = new Cron(single);
    Cron cronCommaSep = new Cron(commaSep);
    Cron cronRange = new Cron(range);
    Cron cronStarSlashed = new Cron(starSlashed);
    Cron cronNonStarSlashed = new Cron(nonStarSlashed);

    // When the cron is queried on what minutes it will run
    String starOutcome = cronStar.getMinutes();
    String singleOutcome = cronSingle.getMinutes();
    String commaSepOutcome = cronCommaSep.getMinutes();
    String rangeOutcome = cronRange.getMinutes();
    String starSlashedOutcome = cronStarSlashed.getMinutes();
    String nonStarSlashedOutcome = cronNonStarSlashed.getMinutes();

    // Then, the cron should report the correct times:
    assertThat("Star output doesn't match", starOutcome,
        is("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59"));
    assertThat("Single digit output doesn't match", singleOutcome, is("12"));
    assertThat("Comma sep output doesn't match", commaSepOutcome, is("1 2 55 56"));
    assertThat("Range output doesn't match", rangeOutcome, is("20 21 22 23 24 25"));
    assertThat("Slash output w/ star doesn't match", starSlashedOutcome, is("0 10 20 30 40 50"));
    assertThat("Slash output doesn't match", nonStarSlashedOutcome, is("20 30 40 50"));
  }

}