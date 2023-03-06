package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import menu.GameOverMenu;
import constants.Sounds;

/**
 * Represents the game over state, which is reached when caught by the NPCs.
 */
public class GameOverState extends GameState {

	public GameOverState(GameModel model, int score, String time, boolean newRecord) {
		super(model);
		GameOverMenu gameOverMenu = new GameOverMenu(model, 40 * 20, 40 * 11, score, time, newRecord);
		getChildren().add(gameOverMenu);
	}

	@Override
	public void draw(GraphicsContext gc) {

	}

	@Override
	public void keyPressed(KeyEvent key) {

	}

	@Override
	public void update() {

	}

	@Override
	public void activate() {
		Sounds.playDollar();
		setVisible(true);
		setManaged(true);
	}

	@Override
	public void deactivate() {
		Sounds.stopDollar();
		setVisible(false);
		setManaged(false);
	}

}
