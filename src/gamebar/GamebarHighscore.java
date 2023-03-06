package gamebar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import main.Score;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Represents a visual in-game element which shows the current highscore.
 */
public class GamebarHighscore extends StackPane {

	private String highscoreString;
	private GamebarText highscore;
	private GamebarText text;
	private HBox highscoreBox;
	private Score score;

	public GamebarHighscore(Score score) {
		this.score = score;
		setAlignment(Pos.CENTER);
		GamebarBox bg = new GamebarBox();
		try {
			File myObj = new File("assets/highscore.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				highscoreString = myReader.nextLine();
				highscore = new GamebarText(highscoreString, 15);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		text = new GamebarText("HIGHSCORE : ", 15);
		highscoreBox = new HBox();
		highscoreBox.setAlignment(Pos.CENTER);
		highscoreBox.getChildren().addAll(text, highscore);
		getChildren().addAll(bg, highscoreBox);
	}

	public void update() {
		if (score.getPoints() > Integer.parseInt(highscoreString)) {
			highscoreBox.getChildren().removeAll(text, highscore);
			highscore = new GamebarText(String.valueOf(score.getPoints()), 15);
			highscoreBox.getChildren().addAll(text, highscore);
		}
	}

}
