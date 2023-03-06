package models;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import constants.Constants;
import main.Score;
import map.MapData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Represents a player, with movement, sprites, collision control and power-up
 * collecting.
 */
public class Player {

	private Point position;
	private Image thief;
	private Image thief2;
	private Image[] thiefs;
	private Image oldman1;
	private Image oldman2;
	private Image[] oldmans;
	private double movement = 2;
	private Score score;
	private boolean disguise;
	private int movementUpdateTimer;
	private int disguiseUpdateTimer;
	private int level;
	private HashMap<Integer, Runnable> itemEffects = new HashMap<Integer, Runnable>();
	private int theChosenImage;
	private MapData mapData;

	public Player(Score score, MapData mapData, int level) {
		this.score = score;
		this.mapData = mapData;
		this.level = level;
		position = new Point(80, 320);

		try {
			thief = new Image(new FileInputStream("assets/thief_run1.png"));
			thief2 = new Image(new FileInputStream("assets/thief_run2.png"));
			oldman1 = new Image(new FileInputStream("assets/oldman1.png"));
			oldman2 = new Image(new FileInputStream("assets/oldman2.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		thiefs = new Image[2];
		oldmans = new Image[2];
		thiefs[0] = thief;
		thiefs[1] = thief2;
		oldmans[0] = oldman1;
		oldmans[1] = oldman2;

		theChosenImage = 0;
		disguise = false;

		itemEffects.put(1, () -> pickCoin());
		itemEffects.put(2, () -> pickPowerUpSpeed());
		itemEffects.put(3, () -> pickPowerUpCamouflage());
	}

	public class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public void update(KeyEvent key) {
		int brickValue;
		move(key);
		changeSprite();
		brickValue = checkIfItem(((int) (position.y) / (int) Constants.BRICK_SIZE),
				((int) (position.x) / (int) Constants.BRICK_SIZE), level);

		if (brickValue != 0) {
			itemEffects.get(brickValue).run();
		}
	}

	public void delegate(GraphicsContext gc) {
		if (gc != null) {
			draw(gc);
		}
	}

	public void draw(GraphicsContext gc) {
		if (disguise == true) {
			gc.drawImage(oldmans[theChosenImage], position.x, position.y, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
		} else {
			gc.drawImage(thiefs[theChosenImage], position.x, position.y, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
		}
	}

	public double getX() {
		return position.x;
	}

	public double getY() {
		return position.y;
	}

	public boolean hasDisguise() {
		return disguise;
	}

	public int checkIfItem(int x, int y, int level) {
		return mapData.getItemPos(((int) (position.y) / (int) Constants.BRICK_SIZE),
				((int) (position.x) / (int) Constants.BRICK_SIZE), level);
	}

	public void pickCoin() {
		mapData.takeItem(((int) (position.y) / 40), ((int) (position.x) / (int) Constants.BRICK_SIZE), level);
		score.addPoints(1);
	}

	public void pickPowerUpSpeed() {
		final double second = 1.0;
		final double nanoPerUpdate = 1000000000.0 / second;
		mapData.takeItem(((int) (position.y) / 40), ((int) (position.x) / (int) Constants.BRICK_SIZE), level);
		movement += 2;

		new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {
				if ((now - lastUpdate) > nanoPerUpdate) {
					movementUpdateTimer += 1;
					lastUpdate = now;
					if (movementUpdateTimer == 20) {
						movement -= 2;
						this.stop();
					}
				}
			}
		}.start();
	}

	public void pickPowerUpCamouflage() {
		final double second = 1.0;
		final double nanoPerUpdate = 1000000000.0 / second;
		mapData.takeItem(((int) (position.y) / 40), ((int) (position.x) / (int) Constants.BRICK_SIZE), level);
		disguise = true;

		new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {
				if ((now - lastUpdate) > nanoPerUpdate) {
					disguiseUpdateTimer += 1;
					lastUpdate = now;
					if (disguiseUpdateTimer == 20) {
						disguise = false;
						this.stop();
					}
				}
			}
		}.start();
	}

	public void move(KeyEvent key) {
		double oldy = position.y;
		double oldx = position.x;
		double positions[] = new double[2];

		if (key.getCode() == KeyCode.W) {
			position.y -= movement;
		} else if (key.getCode() == KeyCode.A) {
			position.x -= movement;
		} else if (key.getCode() == KeyCode.S) {
			position.y += movement;
		} else if (key.getCode() == KeyCode.D) {
			position.x += movement;
		}

		positions = tryWallCollision(oldx, oldy, position.x, position.y);
		position.x = positions[0];
		position.y = positions[1];
	}

	public void changeSprite() {
		if (theChosenImage == 0 && (position.x % (Constants.BRICK_SIZE / 8) == 0)
				&& (position.y % (Constants.BRICK_SIZE / 8) == 0)) {
			theChosenImage = 1;
		} else if (theChosenImage == 1 && (position.x % (Constants.BRICK_SIZE / 8) == 0)
				&& (position.y % (Constants.BRICK_SIZE / 8) == 0)) {
			theChosenImage = 0;
		}
	}

	public double[] tryWallCollision(double oldx, double oldy, double x, double y) {
		double[] positions = new double[2];
		int firstXofFrame = (int) (position.x / Constants.BRICK_SIZE);
		int firstYofFrame = (int) (position.y / Constants.BRICK_SIZE);
		int lastXofFrame = (int) ((position.x + Constants.BRICK_SIZE - 1) / Constants.BRICK_SIZE);
		int lastYofFrame = (int) ((position.y + Constants.BRICK_SIZE - 1) / Constants.BRICK_SIZE);

		if (mapData.getLevelPos(firstYofFrame, firstXofFrame, level) == 0
				|| mapData.getLevelPos(lastYofFrame, lastXofFrame, level) == 0
				|| mapData.getLevelPos(firstYofFrame, lastXofFrame, level) == 0
				|| mapData.getLevelPos(lastYofFrame, firstXofFrame, level) == 0) {
			positions[0] = oldx;
			positions[1] = oldy;
			return positions;
		} else {
			positions[0] = x;
			positions[1] = y;
			return positions;
		}
	}

}
