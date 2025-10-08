package game;

import util.DisplayWord;
import util.HangmanDictionary;

public class Executioner {

  // word that is being guessed
  protected String mySecretWord;

  public Executioner(HangmanDictionary dictionary, int wordLength) {
    createSecretWord(dictionary, wordLength);
  }

  // Returns a secret word.
  public void createSecretWord(HangmanDictionary dictionary, int wordLength) {
    mySecretWord = dictionary.getRandomWord(wordLength).toLowerCase();
  }

  public void printSecretWord() {
    System.out.println(mySecretWord);
  }

  public DisplayWord getDisplayWord() {
    return new DisplayWord(mySecretWord);
  }

  public boolean displayEqualsSecret(DisplayWord display) {
    return display.toString().equals(mySecretWord);
  }

  // Returns true only if given guess is in the secret word.
  public boolean checkGuessInSecret(char guess) {
    return (mySecretWord.indexOf(guess) >= 0);
  }

  public void updateDisplayWord(DisplayWord displayWord, char guess) {
    displayWord.update(guess, mySecretWord);
  }
}
