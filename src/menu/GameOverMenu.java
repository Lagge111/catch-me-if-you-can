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
import states.MainMenuState;
import states.PlayState;

/**
 * Represents the visual game over-menu. This menu is reached is the player is caught by the NPCs. 
 */
public class GameOverMenu extends StackPane {

	public GameOverMenu(GameModel model, int width, int height, int score, String time, boolean newRecord) {
		setAlignment(Pos.CENTER);
		Image image;
		try {
			image = new Image(new FileInputStream("assets/busted.jpg"));
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

		MenuText title = new MenuText("B U S T E D", 30);
		MenuText record = new MenuText("B U T   A   N E W   R E C O R D !", 30);
		MenuText under = new MenuText("You got " + String.valueOf(score) + " coins in " + time + " minutes!", 15);

		MenuButton newGameButton = new MenuButton("T R Y   A G A I N");
		MenuButton backToMenuButton = new MenuButton("B A C K   T O   M E N U");
		MenuButton quitButton = new MenuButton("Q U I T");

		newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Score score = new Score();
				int[] time = new int[2];
				time[0] = 0;
				time[1] = 0;
				model.switchState(new PlayState(model, score, time, 1));
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
		if (newRecord == true) {
			contents.getChildren().add(record);
		}
		contents.getChildren().add(under);
		contents.getChildren().add(newGameButton);
		contents.getChildren().add(backToMenuButton);
		contents.getChildren().add(quitButton);

		getChildren().add(imageView);
		getChildren().add(contents);

	}

}
