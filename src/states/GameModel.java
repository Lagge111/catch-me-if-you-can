package states;

import constants.Sounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * Represents the game model which represents the current state of the game.
 */
public class GameModel {

	private GameState currentState;
	private int states = 1;

	public GameModel() {
		this.currentState = new MainMenuState(this);
		Sounds.playBadBoys();
	}

	public void switchState(GameState nextState) {
		currentState.deactivate();
		states = states + 1;
		currentState = nextState;
		currentState.activate();
	}

	public void keyPressed(KeyEvent key) {
		currentState.keyPressed(key);
	}

	public void update() {
		currentState.update();
	}

	public void draw(GraphicsContext gc) {
		currentState.draw(gc);
	}

	public GameState getCurrentState() {
		return currentState;
	}

	public int getStates() {
		return states;
	}

}
