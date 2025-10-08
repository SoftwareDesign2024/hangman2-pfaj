// Author: Aidan Jimenez
package game;

public class AutoGuesser extends Guesser {
  private static final String LETTERS_ORDERED_BY_FREQUENCY = "etaoinshrldcumfpgwybvkxjqz";
  private String myLetters;
  private int myIndex = 0;

  public AutoGuesser() {
    myLetters = LETTERS_ORDERED_BY_FREQUENCY;
  }

  @Override
  public char getAGuess() {
    return myLetters.charAt(myIndex++);
  }
}
