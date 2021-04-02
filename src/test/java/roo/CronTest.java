package roo;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CronTest {

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
    assertThat(starOutcome,
        is("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59"));
    assertThat(singleOutcome, is("12"));
    assertThat(commaSepOutcome, is("1 2 55 56"));
    assertThat(rangeOutcome, is("20 21 22 23 24 25"));
    assertThat(starSlashedOutcome, is("0 10 20 30 40 50"));
    assertThat(nonStarSlashedOutcome, is("20 30 40 50"));
  }

}