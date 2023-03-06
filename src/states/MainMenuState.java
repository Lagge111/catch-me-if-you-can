package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import menu.MainMenu;
import constants.Sounds;

/**
 * Represents the main manu state, which is reached when opening the game. 
 */
public class MainMenuState extends GameState {

	private MainMenu mainMenu;

	public MainMenuState(GameModel model) {
		super(model);
		mainMenu = new MainMenu(model, 40 * 20, 40 * 11);
		getChildren().add(mainMenu);
	}

	@Override
	public void draw(GraphicsContext gc) {

	}

	@Override
	public void keyPressed(KeyEvent key) {

	}

	@Override
	public void update() {
		mainMenu.update();
	}

	@Override
	public void activate() {
		Sounds.playBadBoys();
		setVisible(true);
		setManaged(true);
	}

	@Override
	public void deactivate() {
		setVisible(false);
		setManaged(false);
	}

}
