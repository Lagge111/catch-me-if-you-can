package models;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.MapData;

/**
 * Represents a generic NPC, its movement, sprite, difficulty, and collision
 * control.
 */
public class NPC {

	private Image police;
	private Image sheriff;
	private Image policeRun1;
	private Image policeRun2;
	private Image policecarRed;
	private Image policecarBlue;
	private Image sheriffRun1;
	private Image sheriffRun2;
	private Image horse1;
	private Image horse2;
	private Image[] policecars;
	private Image[] polices;
	private Image[] sheriffs;
	private Image[] horses;
	private int theChosenImage;
	private double movement;
	private boolean go;
	private double x;
	private double y;
	private double lastXpos;
	private double lastYpos;
	private boolean difficulty;
	private MapData mapData;
	private int level;
	private String direction;
	private String newDirection;
	private String[] directions = { "up", "down", "right", "left" };
	private Random random = new Random();
	private HashMap<String, String> oppositeDirection = new HashMap<String, String>();
	private HashMap<Boolean, Integer> npcDifficulty = new HashMap<Boolean, Integer>();
	private HashMap<Boolean, Image> npcSprite = new HashMap<Boolean, Image>();

	public NPC(MapData mapData, int level, boolean difficulty) {
		this.mapData = mapData;
		this.level = level;
		this.difficulty = difficulty;

		if (level == 1) {
			x = 600.0;
			y = 120.0;
		} else if (level == 2) {
			x = 720.0;
			y = 40.0;
		}

		try {
			policeRun1 = new Image(new FileInputStream("assets/police_run1.png"));
			policeRun2 = new Image(new FileInputStream("assets/police_run2.png"));
			policecarBlue = new Image(new FileInputStream("assets/policecar_blue.png"));
			policecarRed = new Image(new FileInputStream("assets/policecar_red.png"));
			sheriffRun1 = new Image(new FileInputStream("assets/sheriff_run1.png"));
			sheriffRun2 = new Image(new FileInputStream("assets/sheriff_run2.png"));
			horse1 = new Image(new FileInputStream("assets/horse1.png"));
			horse2 = new Image(new FileInputStream("assets/horse2.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		theChosenImage = 0;

		policecars = new Image[2];
		polices = new Image[2];
		sheriffs = new Image[2];
		horses = new Image[2];

		polices[0] = policeRun1;
		polices[1] = policeRun2;
		policecars[0] = policecarBlue;
		policecars[1] = policecarRed;
		sheriffs[0] = sheriffRun1;
		sheriffs[1] = sheriffRun2;
		horses[0] = horse1;
		horses[1] = horse2;

		npcDifficulty.put(true, 1);
		npcDifficulty.put(false, 2);

		npcSprite.put(true, police);
		npcSprite.put(false, sheriff);

		oppositeDirection.put("up", "down");
		oppositeDirection.put("down", "up");
		oppositeDirection.put("left", "right");
		oppositeDirection.put("right", "left");
	}

	public void update() {
		move();
		changeSprite();
		setDataPos();
	}

	public void delegate(GraphicsContext gc) {
		if (gc != null) {
			draw(gc);
		}
	}

	public void draw(GraphicsContext gc) {
		if (x >= SCREEN_WIDTH || y >= SCREEN_HEIGHT) {
			x = 0.0;
			y = 0.0;
		}
		if (level == 1) {
			if (difficulty == true) {
				gc.drawImage(polices[theChosenImage], x, y, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
			} else {
				gc.drawImage(policecars[theChosenImage], x, y, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
			}
		} else if (level == 2) {
			if (difficulty == true) {
				gc.drawImage(sheriffs[theChosenImage], x, y, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
			} else {
				gc.drawImage(horses[theChosenImage], x, y, Constants.BRICK_SIZE, Constants.BRICK_SIZE);
			}
		}
	}

	public String newDirection() {
		return directions[random.nextInt(directions.length)];
	}

	public void tryWallCollision(double oldx, double oldy, double x, double y) {
		int firstXofFrame = (int) (x / Constants.BRICK_SIZE);
		int firstYofFrame = (int) (y / Constants.BRICK_SIZE);
		int lastXofFrame = (int) ((x + Constants.BRICK_SIZE - 1) / Constants.BRICK_SIZE);
		int lastYofFrame = (int) ((y + Constants.BRICK_SIZE - 1) / Constants.BRICK_SIZE);

		if (mapData.getLevelPos(firstYofFrame, firstXofFrame, level) == 0
				|| mapData.getLevelPos(lastYofFrame, lastXofFrame, level) == 0
				|| mapData.getLevelPos(firstYofFrame, lastXofFrame, level) == 0
				|| mapData.getLevelPos(lastYofFrame, firstXofFrame, level) == 0) {
			x = oldx;
			y = oldy;
			direction = oppositeDirection.get(direction);
		}
	}

	public void move() {
		double oldY = y;
		double oldX = x;
		HashMap<String, Double> move = new HashMap<String, Double>();

		movement = npcDifficulty.get(difficulty);
		move.put("up", y - movement);
		move.put("left", x - movement);
		move.put("down", y + movement);
		move.put("right", x + movement);

		if (((x % Constants.BRICK_SIZE == 0) && (y % Constants.BRICK_SIZE == 0)) && (mapData.getLevelPos((int) ((y) / Constants.BRICK_SIZE), (int) ((x) / Constants.BRICK_SIZE), level) == 1
				|| mapData.getLevelPos((int) ((y) / Constants.BRICK_SIZE), (int) ((x) / Constants.BRICK_SIZE), level) >= 4)) {
			do {
				newDirection = newDirection();
				if (newDirection == oppositeDirection.get(direction)) {
					go = false;
				} else {
					go = true;
				}
			} while (go == false);
			direction = newDirection;
		}
		if (direction == "up" || direction == "down") {
			y = move.get(direction);
			tryWallCollision(oldX, oldY, x, y);
		} else {
			x = move.get(direction);
			tryWallCollision(oldX, oldY, x, y);
		}
	}

	public void changeSprite() {
		if (theChosenImage == 0 && (x % (Constants.BRICK_SIZE / 8) == 0) && (y % (Constants.BRICK_SIZE / 8) == 0)) {
			theChosenImage = 1;
		} else if (theChosenImage == 1 && (x % (Constants.BRICK_SIZE / 8) == 0)
				&& (y % (Constants.BRICK_SIZE / 8) == 0)) {
			theChosenImage = 0;
		}
	}

	public void setDataPos() {

		mapData.setNpcPos((int) lastYpos, (int) lastXpos, 0);
		mapData.setNpcPos(((int) (y) / (int) Constants.BRICK_SIZE), ((int) (x) / (int) Constants.BRICK_SIZE), 1);

		lastXpos = ((int) (x) / (int) Constants.BRICK_SIZE);
		lastYpos = ((int) (y) / (int) Constants.BRICK_SIZE);
	}

}
