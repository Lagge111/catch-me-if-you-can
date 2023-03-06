package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import main.Score;
import menu.LevelCompletedMenu;
import constants.Sounds;

/**
 * Represents the level completed state, which is reached when all the coins of
 * the current level has been picked up.
 */
public class LevelCompletedState extends GameState {

	public LevelCompletedState(GameModel model, Score score, int[] time, int level) {
		super(model);
		LevelCompletedMenu levelCompletedMenu = new LevelCompletedMenu(model, 40 * 20, 40 * 11, score, time, level);
		getChildren().add(levelCompletedMenu);
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
		Sounds.playWest();
		setVisible(true);
		setManaged(true);
	}

	@Override
	public void deactivate() {
		Sounds.stopWest();
		setVisible(false);
		setManaged(false);
	}

}
