package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import menu.HighscoreMenu;

/**
 * Represents the highscore state, which is reached when the player clicks on
 * "Highscore" on the main menu.
 */
public class HighScoreState extends GameState {

	public HighScoreState(GameModel model) {
		super(model);
		HighscoreMenu highScoreMenu = new HighscoreMenu(model, 40 * 20, 40 * 11);
		getChildren().add(highScoreMenu);
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
		setVisible(true);
		setManaged(true);
	}

	@Override
	public void deactivate() {
		setVisible(false);
		setManaged(false);
	}

}
