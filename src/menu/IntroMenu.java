package menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import constants.Sounds;
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
import javafx.scene.text.TextAlignment;
import main.Score;
import states.GameModel;
import states.PlayState;

/**
 * Represents the visual intro menu which is accessed by clicking "New game" on
 * the main menu. This menu gives the player a bit of context for the game, and
 * shows the player which controls to use.
 */
public class IntroMenu extends StackPane {

	public IntroMenu(GameModel model, int width, int height) {
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

		VBox textBox = new VBox();
		textBox.setAlignment(Pos.CENTER);
		textBox.maxWidth(200);
		MenuText info = new MenuText("You just did the heist of the century! Everything would be perfect, just if\n"
				+ "your bag didn't get broken on the run... all the loot are on the streets...\n"
				+ "You need to collect every single coin before the cops get you!\n" + "\n" + "HOW TO PLAY\n"
				+ "W, A, S & D for movements\n"
				+ "The bank is a ''safe spot'', the cops never going to believe you are back again\n" + "\n"
				+ "POWER-UPS\n" + "The Camouflage - you melt in with the crowd, cops won't get you\n"
				+ "The Running Shoes - you can move faster\n" + "\n" + "Good luck...", 17);
		info.setTextAlignment(TextAlignment.CENTER);
		textBox.getChildren().add(info);

		MenuButton goButton = new MenuButton("G O !");

		goButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Score score = new Score();
				int[] time = new int[2];
				time[0] = 0;
				time[1] = 0;
				Sounds.playCitySound();
				model.switchState(new PlayState(model, score, time, 1));
			}
		});

		contents.getChildren().add(textBox);
		contents.getChildren().add(goButton);

		getChildren().add(imageView);
		getChildren().add(contents);
	}

}
