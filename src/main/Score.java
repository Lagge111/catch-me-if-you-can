package main;

/**
 * Represents a score tracker, which keeps track of the current in-game score,
 * and tracks the total score.
 */
public class Score {

	private int totalScore;

	public Score() {
		totalScore = 0;
	}

	public void addPoints(int i) {
		totalScore += 1;
	}

	public int getPoints() {
		return totalScore;
	}

}
