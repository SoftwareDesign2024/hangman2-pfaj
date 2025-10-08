// Author: Aidan Jimenez
package game;

import util.HangmanDictionary;
import util.ConsoleReader;

public class AutoExecutioner extends Executioner {

  public AutoExecutioner(HangmanDictionary dictionary, int wordLength) {
    super(dictionary, wordLength);
  }

  @Override
  public void createSecretWord(HangmanDictionary dictionary, int wordLength) {
    mySecretWord = getSecretWord(dictionary, wordLength);
  }

  private String getSecretWord(HangmanDictionary dictionary, int wordLength) {
    String result = ConsoleReader.promptString("Choose a secret word that is " + wordLength + " letters long: ");
    while (!dictionary.contains(result, wordLength)) {
      result = ConsoleReader.promptString("That word is not recognized, please choose another: ");
    }
    return result;
  }
}
