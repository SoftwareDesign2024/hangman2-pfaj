// Author: Aidan Jimenez
package game;

import util.HangmanDictionary;
import util.DisplayWord;

import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CheatingExecutioner extends Executioner {
  // executioner state
  private List<String> myRemainingWords;
  private DisplayWord myDisplayWord;

  public CheatingExecutioner(HangmanDictionary dictionary, int wordLength) {
    super(dictionary, wordLength);
    myRemainingWords = dictionary.getWords(wordLength);
    myDisplayWord = getDisplayWord();
  }

  @Override
  public boolean checkGuessInSecret(char guess) {
    cheat(guess);
    return (mySecretWord.indexOf(guess) >= 0);
  }

  public void cheat(char guess) {
    // create template of guesses and find one with most matching remaining words
    HashMap<DisplayWord, List<String>> templatedWords = new HashMap<DisplayWord, List<String>>();
    for (String w : myRemainingWords) {
      DisplayWord template = new DisplayWord(myDisplayWord);
      template.update(guess, w);
      if (!templatedWords.containsKey(template)) {
        templatedWords.put(template, new ArrayList<>());
      }
      templatedWords.get(template).add(w);
    }
    int max = 0;
    DisplayWord maxKey = new DisplayWord("");
    for (Entry<DisplayWord, List<String>> entry : templatedWords.entrySet()) {
      // System.out.println(entry.getValue());
      if (entry.getValue().size() > max) {
        max = entry.getValue().size();
        maxKey = entry.getKey();
      }
    }

    // update secret word to match template of guesses
    myRemainingWords = templatedWords.get(maxKey);
    Collections.shuffle(myRemainingWords);
    mySecretWord = myRemainingWords.get(0);
    myDisplayWord = maxKey;
  }

}
