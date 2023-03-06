package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import menu.IntroMenu;
import constants.Sounds;

/**
 * Represents the intro state, which is reached when clicking "New game" on the main menu.
 */
public class IntroState extends GameState {

	public IntroState(GameModel model) {
		super(model);
		IntroMenu introMenu = new IntroMenu(model, 40 * 20, 40 * 11);
		getChildren().add(introMenu);
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
		Sounds.stopBadBoys();
		Sounds.playRace();
		setVisible(true);
		setManaged(true);
	}

	@Override
	public void deactivate() {
		setVisible(false);
		setManaged(false);
	}

}
