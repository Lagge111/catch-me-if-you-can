package menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import states.GameModel;
import states.HighScoreState;
import states.IntroState;

/**
 * Represents the visual main menu of the game. This menu is accessed by opening
 * the game, and gives the player options to either start a new game, look at
 * the current highscore, or to quit the game.
 */
public class MainMenu extends StackPane {

	private ImageView viewLights1;
	private ImageView viewLights2;
	private double showLights1 = 0.50;
	private double showLights2 = 0.00;
	private boolean raiseLights1 = false;
	private boolean raiseLights2 = true;

	public MainMenu(GameModel model, int width, int height) {
		setAlignment(Pos.CENTER);
		Image image;
		Image lights1;
		Image lights2;

		try {
			image = new Image(new FileInputStream("assets/cmiyc.jpg"));
			lights1 = new Image(new FileInputStream("assets/lights1.png"));
			lights2 = new Image(new FileInputStream("assets/lights2.png"));
		} catch (FileNotFoundException e) {
			image = null;
			lights1 = null;
			lights2 = null;
			e.printStackTrace();
		}

		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(width);
		imageView.setPreserveRatio(true);

		viewLights1 = new ImageView();
		viewLights1.setImage(lights1);
		viewLights1.setFitWidth(width);
		viewLights1.setPreserveRatio(true);

		viewLights2 = new ImageView();
		viewLights2.setImage(lights2);
		viewLights2.setFitWidth(width);
		viewLights2.setPreserveRatio(true);

		VBox contents = new VBox();
		contents.setSpacing(20);
		contents.setAlignment(Pos.CENTER);

		StackPane top = new StackPane();
		TitleFrame tf = new TitleFrame();
		MenuText title = new MenuText("CATCH ME IF YOU CAN", 50);
		top.getChildren().addAll(tf, title);

		VBox under = new VBox();
		under.setAlignment(Pos.CENTER);
		MenuText by2 = new MenuText("B Y", 10);
		MenuText name2 = new MenuText("A-3 STUDIO", 20);
		under.getChildren().addAll(by2, name2);

		MenuButton newGameButton = new MenuButton("N E W   G A M E");
		MenuButton highScoreButton = new MenuButton("H I G H S C O R E");
		MenuButton quitButton = new MenuButton("Q U I T");

		newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				model.switchState(new IntroState(model));
			}
		});

		highScoreButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				model.switchState(new HighScoreState(model));
			}
		});

		quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Platform.exit();
			}
		});

		contents.getChildren().add(top);
		contents.getChildren().add(newGameButton);
		contents.getChildren().add(highScoreButton);
		contents.getChildren().add(quitButton);
		contents.getChildren().add(under);

		getChildren().addAll(imageView, viewLights1, viewLights2);
		getChildren().add(contents);

	}

	public void update() {
		if (showLights1 > 0.6) {
			raiseLights1 = false;
		} else if (showLights1 < 0.0) {
			raiseLights1 = true;
		}

		if (showLights2 > 0.6) {
			raiseLights2 = false;
		} else if (showLights2 < 0.0) {
			raiseLights2 = true;
		}

		if (raiseLights1 == true) {
			showLights1 = showLights1 + 0.02;
		} else {
			showLights1 = showLights1 - 0.02;
		}

		if (raiseLights2 == true) {
			showLights2 = showLights2 + 0.02;
		} else {
			showLights2 = showLights2 - 0.02;
		}

		viewLights1.setOpacity(showLights1);
		viewLights2.setOpacity(showLights2);
	}

}
