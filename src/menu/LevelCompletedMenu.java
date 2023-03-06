package menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import constants.Sounds;
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
 * Represents the visual level completed menu, which is accessed by completing the
 * current level. Gives the player the option to either continue to next level,
 * or to return to the main menu.
 */
public class LevelCompletedMenu extends StackPane {

	public LevelCompletedMenu(GameModel model, int width, int height, Score score, int[] time, int level) {
		setAlignment(Pos.CENTER);
		Image image;
		try {
			image = new Image(new FileInputStream("assets/desert.jpg"));
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

		MenuText title = new MenuText("L E V E L   C O M P L E T E D", 30);
		MenuText under = new MenuText("Are you ready to enter the desert?", 15);

		MenuButton nextLevelButton = new MenuButton("N E X T   M A P");
		MenuButton backToMenuButton = new MenuButton("B A C K   T O   M E N U");
		MenuButton quitButton = new MenuButton("Q U I T");

		nextLevelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Sounds.playClintan();
				model.switchState(new PlayState(model, score, time, 2));
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
		contents.getChildren().add(nextLevelButton);
		contents.getChildren().add(backToMenuButton);
		contents.getChildren().add(quitButton);
		getChildren().add(imageView);
		getChildren().add(contents);
	}

}
