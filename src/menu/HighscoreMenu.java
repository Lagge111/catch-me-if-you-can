package menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Highscore;
import states.GameModel;
import states.MainMenuState;

/**
 * Represents the visual highscore menu. This menu is reached if the player clicks
 * "Highscore" on the main menu.
 */
public class HighscoreMenu extends StackPane {


	public HighscoreMenu(GameModel model, int width, int height) {
		setAlignment(Pos.CENTER);
		Image image;
		String theHighscore = "0";
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

		StackPane top = new StackPane();

		Text title = new Text("H I G H S C O R E");
		title.setEffect(ds);
		title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		title.setFill(Color.WHITE);

		top.getChildren().addAll(title);

		try {
			File myObj = new File("assets/highscore.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				theHighscore = myReader.nextLine();
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		VBox scoreBoard = new VBox();
		scoreBoard.setAlignment(Pos.CENTER);
		MenuText first = new MenuText("MOST COINS COLLECTED: " + theHighscore, 15);

		scoreBoard.getChildren().addAll(first);

		MenuButton reset = new MenuButton("R E S E T");

		reset.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				scoreBoard.getChildren().remove(first);
				Highscore.reset();
				MenuText first = new MenuText("MOST COINS COLLECTED: 0", 15);
				scoreBoard.getChildren().add(first);
			}
		});
		MenuButton backToMenuButton = new MenuButton("B A C K   T O   M E N U");

		backToMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				model.switchState(new MainMenuState(model));
			}
		});

		contents.getChildren().add(top);
		contents.getChildren().add(scoreBoard);
		contents.getChildren().add(reset);
		contents.getChildren().add(backToMenuButton);
		;

		getChildren().add(imageView);
		getChildren().add(contents);

	}

}
