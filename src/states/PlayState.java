package states;

import constants.Constants;
import constants.Sounds;
import gamebar.Gamebar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Highscore;
import main.Score;
import map.MapData;
import map.MapGenerator;
import models.NPC;
import models.Player;

/**
 * Represents the play state, which is reached when clicking "New game" on the
 * main menu, after entering the intro state.
 */
public class PlayState extends GameState {

	private GameModel model;
	private Player player;
	private MapGenerator map;
	private NPC NPC1;
	private NPC NPC2;
	private NPC NPC3;
	private Gamebar gamebar;
	private MapData mapData;
	private int level;
	private Score score;

	public PlayState(GameModel model, Score score, int[] time, int level) {
		super(model);
		this.model = model;
		this.level = level;
		this.score = score;
		boolean easy = true;
		boolean hard = false;
		mapData = new MapData();
		map = new MapGenerator(mapData, level);
		player = new Player(score, mapData, level);
		NPC1 = new NPC(mapData, level, easy);
		NPC2 = new NPC(mapData, level, easy);
		NPC3 = new NPC(mapData, level, hard);
		gamebar = new Gamebar(model, score, time);

		Rectangle r = new Rectangle(0, 0, Constants.BRICK_SIZE * MapData.NO_OF_COL, Constants.BRICK_SIZE * MapData.NO_OF_ROW);
		r.setFill(Color.TRANSPARENT);
		getChildren().add(r);
		getChildren().add(gamebar);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.clearRect(0, 0, 1000, 1000);
		map.delegate(gc);
		player.delegate(gc);
		NPC1.delegate(gc);
		NPC2.delegate(gc);
		NPC3.delegate(gc);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.A || key.getCode() == KeyCode.S
				|| key.getCode() == KeyCode.D) {
			player.update(key);
		}
	}

	@Override
	public void update() {
		NPC1.update();
		NPC2.update();
		NPC3.update();
		gamebar.update();

		if (mapData.tryCollision(((int) (player.getY()) / (int) Constants.BRICK_SIZE),
				((int) (player.getX()) / (int) Constants.BRICK_SIZE)) == 1) {
			boolean newRecord = Highscore.saveIfHigher(score.getPoints());
			model.switchState(new GameOverState(model, score.getPoints(), gamebar.showTime(), newRecord));
		}

		if (mapData.getNoOfCoinsLevel1() == score.getPoints() && level == 1) {
			model.switchState(new LevelCompletedState(model, score, gamebar.getTime(), level));
		}

		if (mapData.getNoOfCoinsLevel1() + mapData.getNoOfCoinsLevel2() == score.getPoints()) {
			model.switchState(new VictoryState(model, score, gamebar.showTime()));
		}
	}

	@Override
	public void activate() {
		setVisible(true);
		setManaged(true);
	}

	@Override
	public void deactivate() {
		Sounds.stopCitySound();
		Sounds.stopRace();
		Sounds.stopClintan();
		setVisible(false);
		setManaged(false);
	}

}
