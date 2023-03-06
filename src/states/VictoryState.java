package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import main.Score;
import menu.VictoryMenu;
import constants.Sounds;

/**
 * Represents the victory state, which is reached when completing both game
 * levels, i.e. collecting all the coins on the two level.
 */
public class VictoryState extends GameState {

	public VictoryState(GameModel model, Score score, String time) {
		super(model);
		VictoryMenu victoryMenu = new VictoryMenu(model, 40 * 20, 40 * 11, score, time);
		getChildren().add(victoryMenu);
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
		Sounds.playMove();
		setVisible(true);
		setManaged(true);
	}

	@Override
	public void deactivate() {
		Sounds.stopMove();
		setVisible(false);
		setManaged(false);
	}

}
