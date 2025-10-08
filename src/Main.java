import game.HangmanGame;
import game.AutoGuesser;
import game.CheatingExecutioner;
import game.Executioner;
import game.Guesser;
import util.HangmanDictionary;

/**
 * This class launches the Hangman game and plays once.
 * 
 * @author Michael Hewner
 * @author Mac Mason
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class Main {
  public static final String DICTIONARY = "data/lowerwords.txt";
  public static final int NUM_LETTERS = 6;
  public static final int NUM_MISSES = 8;

  public static void main(String[] args) {
    Executioner executioner = new Executioner(new HangmanDictionary(DICTIONARY), NUM_LETTERS);
    Guesser guesser = new Guesser();

    new HangmanGame(executioner, guesser, NUM_MISSES).play();
  }
}
