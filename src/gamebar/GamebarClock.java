package gamebar;

import javafx.animation.AnimationTimer;

/**
 * Represents a gamebar clock, which keeps track of the time played, which is
 * displayed in the GamebarTime class.
 */
public class GamebarClock {

	private String time;
	private int seconds;
	private int minutes;
	private String minutesAdd;
	private String secondsAdd;
	private int[] timeArray;

	public GamebarClock(int[] timeInput) {
		minutesAdd = "0";
		secondsAdd = "0";
		timeArray = new int[2];
		minutes = timeInput[0];
		seconds = timeInput[1];

		final double targetTimePerSecond = 1;
		final double nanoPerUpdate = 1000000000.0 / targetTimePerSecond;

		new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {
				if ((now - lastUpdate) > nanoPerUpdate) {
					seconds += 1;
					if (seconds % 60 == 0) {
						minutes += 1;
						seconds = 0;
					}
					if (minutes > 9) {
						minutesAdd = "";
					}
					if (seconds > 9) {
						secondsAdd = "";
					}
					time = (minutesAdd + String.valueOf(minutes) + "." + secondsAdd + String.valueOf(seconds));
					timeArray[0] = minutes;
					timeArray[1] = seconds;
					lastUpdate = now;
				}
			}
		}.start();
	}

	public String showTime() {
		return time;
	}

	public int[] getTime() {
		return timeArray;
	}

}
