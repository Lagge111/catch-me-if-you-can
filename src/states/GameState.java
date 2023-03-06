package states;

import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

/**
 * Represents the state of the game. Super class to the different game states,
 * e.g. PlayState, MainMenuState etc.
 */
public abstract class GameState extends VBox {

	public GameState(GameModel model) {
		setAlignment(Pos.CENTER);
	}

	public abstract void update();

	public abstract void draw(GraphicsContext gc);

	public abstract void keyPressed(KeyEvent key);

	public void drawBg(GraphicsContext gc, Color color) {
		gc.setFill(color);
		gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	public abstract void activate();

	public abstract void deactivate();

}
