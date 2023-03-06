package menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Score;
import states.GameModel;
import states.HighScoreState;
import states.MainMenuState;

/**
 * Represents the visual victory menu, which is accessed by completing both
 * levels, i.e. when winning the game. Shows how many coins the player
 * collected, and the time spent doing so. Gives the player options to either
 * look at the current highscore, go back to the main menu, or to quit the game.
 */
public class VictoryMenu extends StackPane {

	public VictoryMenu(GameModel model, int width, int height, Score score, String time) {
		setAlignment(Pos.CENTER);
		Image image;
		try {
			image = new Image(new FileInputStream("assets/cmiyc.jpg"));
		} catch (FileNotFoundException e) {
			image = null;
			e.printStackTrace();
		}

		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(width);
		imageView.setPreserveRatio(true);
		imageView.setEffect(new GaussianBlur());

		VBox contents = new VBox();
		contents.setSpacing(20);
		contents.setAlignment(Pos.CENTER);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

		MenuText title = new MenuText("G A M E   C O M P L E T E D", 30);
		MenuText under = new MenuText("You got " + String.valueOf(score.getPoints()) + " coins in " + time + " minutes!", 15);

		MenuButton highscoreButton = new MenuButton("H I G H S C O R E");
		MenuButton backToMenuButton = new MenuButton("B A C K   T O   M E N U");
		MenuButton quitButton = new MenuButton("Q U I T");

		highscoreButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				model.switchState(new HighScoreState(model));
			}
		});

		backToMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				model.switchState(new MainMenuState(model));
			}
		});

		quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Platform.exit();
			}
		});

		contents.getChildren().add(title);
		contents.getChildren().add(under);
		contents.getChildren().add(highscoreButton);
		contents.getChildren().add(backToMenuButton);
		contents.getChildren().add(quitButton);
		getChildren().add(imageView);
		getChildren().add(contents);
	}

}
