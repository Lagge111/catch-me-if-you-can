package main;

import states.GameModel;
import constants.Constants;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Represents the main class which adds content to the Stage and runs the game
 * loop.
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage gameStage) throws Exception {
		gameStage.setTitle("Catch Me If You Can");
		gameStage.setWidth(Constants.SCREEN_WIDTH);
		gameStage.setHeight(Constants.SCREEN_HEIGHT);

		GameModel model = new GameModel();
		GameFrame frame = new GameFrame(model, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		final double targetFps = 50.0;
		final double nanoPerUpdate = 1000000000.0 / targetFps;

		Scene gameScene = new Scene(frame);
		gameStage.setScene(gameScene);

		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				model.keyPressed(event);
			}
		});

		new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {
				if ((now - lastUpdate) > nanoPerUpdate) {
					model.update();
					frame.repaint();
					lastUpdate = now;
				}
			}
		}.start();

		gameStage.show();
	}

}
