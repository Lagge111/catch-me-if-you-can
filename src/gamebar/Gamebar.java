package gamebar;

import main.Score;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Highscore;
import states.GameModel;
import states.MainMenuState;

/**
 * Represents a Gamebar which contains visual in-game elements to help the player keep track of progress. 
 */
public class Gamebar extends StackPane {

	private GamebarHighscore highscoreBox;
	private GamebarTime timeBox;
	private GamebarCoins coinsBox;

	public Gamebar(GameModel model, Score score, int[] time) {
		setAlignment(Pos.CENTER);
		Rectangle bg = new Rectangle();
		bg.setWidth(40 * 20);
		bg.setHeight(40);
		bg.setFill(Color.rgb(30, 30, 30, 1));
		bg.setStroke(Color.GHOSTWHITE);

		highscoreBox = new GamebarHighscore(score);
		timeBox = new GamebarTime(time);
		coinsBox = new GamebarCoins(score);
		GamebarButton endButton = new GamebarButton("END");
		
		endButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Highscore.saveIfHigher(score.getPoints());
				model.switchState(new MainMenuState(model));
			}
		});

		HBox contents = new HBox();
		contents.setAlignment(Pos.CENTER);
		contents.setSpacing(50);
		contents.getChildren().addAll(highscoreBox, timeBox, coinsBox, endButton);
		getChildren().addAll(bg, contents);
	}

	public void update() {
		coinsBox.update();
		highscoreBox.update();
	}
	
	public int[] getTime() {
		return timeBox.getTime();
	}
	
	public String showTime() {
		return timeBox.showTime();
	}

}
