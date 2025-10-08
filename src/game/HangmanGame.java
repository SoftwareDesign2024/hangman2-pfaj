package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;

/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class HangmanGame {
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  private Executioner myExecutioner;
  private Guesser myGuesser;
  // how many guesses are remaining
  private int myNumGuessesLeft;
  // what is shown to the user
  private DisplayWord myDisplayWord;
  // tracks letters guessed
  private StringBuilder myLettersLeftToGuess;

  /**
   * Create Hangman game with the given dictionary of words to play a game with
   * words
   * of the given length and giving the user the given number of chances.
   */
  public HangmanGame(Executioner executioner, Guesser guesser, int numGuesses) {
    myExecutioner = executioner;
    myGuesser = guesser;
    myNumGuessesLeft = numGuesses;
    myLettersLeftToGuess = new StringBuilder(ALPHABET);
    myDisplayWord = myExecutioner.getDisplayWord();
  }

  /**
   * Play one complete game.
   */
  public void play() {
    boolean gameOver = false;
    while (!gameOver) {
      printStatus();

      char guess = myGuesser.getAGuess();

      makeGuess(guess);
      if (isGameLost()) {
        System.out.println("YOU ARE HUNG!!!");
        gameOver = true;
      } else if (isGameWon()) {
        System.out.println("YOU WIN!!!");
        gameOver = true;
      }
    }
    System.out.println("The secret word was ");
    myExecutioner.printSecretWord();
  }

  // Process a guess by updating the necessary internal state.
  private void makeGuess(char guess) {
    // do not count repeated guess as a miss
    int index = myLettersLeftToGuess.indexOf("" + guess);
    if (index >= 0) {
      recordGuess(index);
      if (!checkGuessInSecret(guess)) {
        myNumGuessesLeft -= 1;
      }
    }
  }

  // Record that a specific letter was guessed
  private void recordGuess(int index) {
    myLettersLeftToGuess.deleteCharAt(index);
  }

  // Returns true only if given guess is in the secret word.
  private boolean checkGuessInSecret(char guess) {
    if (myExecutioner.checkGuessInSecret(guess)) {
      myExecutioner.updateDisplayWord(myDisplayWord, guess);
      return true;
    }
    return false;
  }

  // Returns true only if the guesser has guessed all letters in the secret word.
  private boolean isGameWon() {
    return myExecutioner.displayEqualsSecret(myDisplayWord);
  }

  // Returns true only if the guesser has used up all their chances to guess.
  private boolean isGameLost() {
    return myNumGuessesLeft == 0;
  }

  // Print game stats
  private void printStatus() {
    System.out.println(myDisplayWord);
    System.out.println("# misses left = " + myNumGuessesLeft);
    System.out.println("letters not yet guessed = " + myLettersLeftToGuess);
    // NOT PUBLIC, but makes it easier to test
    // System.out.println("*** ");
    myExecutioner.printSecretWord();
    System.out.println();
  }
}
