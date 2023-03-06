package main;

import states.GameModel;
import javafx.scene.layout.StackPane;

/**
 * Represents a GameFrame which creates the game panel.
 */
public class GameFrame extends StackPane {

	private GamePanel g;
	private GameModel model;
	private int noOfStatesBefore = 1;

	public GameFrame(GameModel model, int width, int height) {
		this.model = model;
		g = new GamePanel(model, 40 * 20, 40 * 11);
		getChildren().add(g);
		getChildren().add(model.getCurrentState());
	}

	public void repaint() {
		int noOfStatesNow = model.getStates();
		if (noOfStatesNow != noOfStatesBefore) {
			getChildren().removeAll(g, model.getCurrentState());
			getChildren().addAll(g, model.getCurrentState());
		} else {
			g.repaint();
		}
		noOfStatesBefore = noOfStatesNow;
	}

}
