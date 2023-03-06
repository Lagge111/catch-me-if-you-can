package main;

import states.GameModel;
import javafx.scene.canvas.Canvas;

/**
 * Represents a GameModel which supplies the game with a GraphicsContext object
 * for painting different states. Also controls the size of the game-surface.
 */
public class GamePanel extends Canvas {

	private GameModel model;

	public GamePanel(final GameModel model, int width, int height) {
		this.model = model;
		this.setWidth(width);
		this.setHeight(height);
	}

	public void repaint() {
		model.draw(getGraphicsContext2D());
	}
	
}
