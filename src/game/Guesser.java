package game;

import util.ConsoleReader;

public class Guesser {
	
	public char getAGuess() {
		String guess = ConsoleReader.promptString("Make a guess: ");
		while(!(guess.length() == 1 && Character.isAlphabetic(guess.charAt(0)))) {
			guess = ConsoleReader.promptString("Make a guess: ");
		}
		return guess.toLowerCase().charAt(0);
     }
}
