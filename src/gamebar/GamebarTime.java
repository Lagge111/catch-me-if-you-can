package gamebar;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Represents a time counter which help the player keep track of time played.
 */
public class GamebarTime extends StackPane {

	private GamebarClock clock;
	private GamebarText time;

	public GamebarTime(int[] timeInput) {
		GamebarBox bg = new GamebarBox();
		HBox timeBox = new HBox();
		timeBox.setAlignment(Pos.CENTER);
		GamebarText text = new GamebarText("TIME : ", 15);
		clock = new GamebarClock(timeInput);
		time = new GamebarText(clock.showTime(), 15);
		timeBox.getChildren().addAll(text, time);
		getChildren().addAll(bg, timeBox);
		final double targetTimePerSecond = 1;
		final double nanoPerUpdate = 1000000000.0 / targetTimePerSecond;

		new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {
				if ((now - lastUpdate) > nanoPerUpdate) {
					timeBox.getChildren().removeAll(text, time);
					time = new GamebarText(clock.showTime(), 15);
					timeBox.getChildren().addAll(text, time);
					lastUpdate = now;
				}
			}
		}.start();
	}

	public String showTime() {
		return clock.showTime();
	}

	public int[] getTime() {
		return clock.getTime();
	}

}
