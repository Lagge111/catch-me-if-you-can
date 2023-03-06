package gamebar;

import main.Score;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Represents a coin counter which helps the player keep track of progress.
 */
public class GamebarCoins extends StackPane {

	private int noOfCoins = 0;
	private HBox coinsBox;
	private GamebarText coins;
	private GamebarText text;
	private Score score;

	public GamebarCoins(Score score) {
		this.score = score;
		setAlignment(Pos.CENTER);
		GamebarBox bg = new GamebarBox();
		coinsBox = new HBox();
		coinsBox.setAlignment(Pos.CENTER);
		text = new GamebarText("COINS : ", 15);
		coins = new GamebarText(String.valueOf(noOfCoins), 15);
		coinsBox.getChildren().addAll(text, coins);
		getChildren().addAll(bg, coinsBox);

	}

	public void update() {
		noOfCoins = score.getPoints();
		coinsBox.getChildren().removeAll(text, coins);
		coins = new GamebarText(String.valueOf(noOfCoins), 15);
		coinsBox.getChildren().addAll(text, coins);
	}

}
