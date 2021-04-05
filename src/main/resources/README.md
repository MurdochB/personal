# The Roo Cron

The Cron class represents a cron job that can be built from a list of string arguments.

There is a public method that can be called to print out a table: `printTable()`
There's also some public methods that can get the individual lines e.g. `getMinutes()`. 

## Installing / running

The easiest way to run this project is to import it into your favourite IDE, and use the run configurations to pass in the arguments for a cron job you'd like to see the table for.
There is a Demo class which will take the arguments you set up in the run configuration, and simply print the table for you.

If you don't want to use an IDE, and just want to run the Demo class from the command line, you will have to compile it yourself.
In a terminal, and navigate to the roo package: `src/main/java/roo`
compile the Cron class with `javac -d . Cron.java`
compile the Demo class with `javac -d . Demo.java`
Run `java Demo.java` followed by your arguments (note, some terminals behave strangely with '*', you may have to escape these characters '\*' or wrap them in quotes '"*"')

## Input / arguments

The first 5 inputs are for the times the job should run, and they can be in a few formats:

Star (e.g. [*]) 
Which represents all the possible values for that field.

A single digit (e.g [19])
Which represents just that one value for that field.

Comma separated digits (e.g. [6,7,10,11])
Which represents all the values present only for that field.

Range separated digits (e.g. [10-20])
Which represents all the values within the range (inclusive, both sides). 
For example, the range [5-9] would give the values {5,6,7,8,9}

Slash separated digits (e.g. [0/10 or 30/10])
Which will start from the first number, and increase in steps of the second number until the maximum
You can also use a "*" as the first digit, and it will behave the same as the minimum for that field.
So for example, for the minute field, the slashed input of [11/10] will give {11,21,31,41,51}

Any of the above formats can be used for any field, though some will be less useful. 

## Fields

The first argument is for the minute field. The minimum is 0, and the max is 59.
The second argument is for the hour field. The minimum is 0, and the max is 23.
The third argument is for the day of the month field. The minimum is 1, and the max is 31.
The forth argument is for the month field. The minimum is 1 (representing January), and the max is 12 (representing December)
The fifth argument is for the day of the week field. The minimum is 1 (representing Monday), the maximum is 7 (representing Sunday)


The Cron class can throw exceptions if you attempt to create one with invalid arguments.
This can happen if:
- values are too small for a field. For example, attempting to create a cron job with the month field set to 0.
- values are too large for a field. For example, attempting to create a cron job with the month field set to 13.
- not enough arguments are passed, so some fields would be blank
- arguments not in the above formats for fields, for example strings ("one" "one" "one" "one" "one" "command")