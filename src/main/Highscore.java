package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a highscore manager, which writes new highscores to a file, or
 * resets the highscore on the file if the player chooses to.
 */
public class Highscore {

	public static boolean saveIfHigher(int collectedCoins) {
		boolean newRecord = false;
		String currentHighscore = "0";
		try {
			File highscoreFile = new File("assets/highscore.txt");
			Scanner scanner = new Scanner(highscoreFile);
			while (scanner.hasNextLine()) {
				currentHighscore = scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fel vid inläsning av textfil.");
		}

		if (collectedCoins > Integer.parseInt(currentHighscore)) {
			try {
				FileWriter printer = new FileWriter("assets/highscore.txt");
				printer.write(String.valueOf(collectedCoins));
				printer.close();
			} catch (IOException e) {
				System.out.println("Fel vid inmatning på textfil.");
			}
			newRecord = true;
		}
		return newRecord;
	}

	public static void reset() {
		try {
			FileWriter printer = new FileWriter("assets/highscore.txt");
			printer.write(String.valueOf(0));
			printer.close();
		} catch (IOException e) {
			System.out.println("Fel vid inmatning på textfil.");
		}
	}

}
