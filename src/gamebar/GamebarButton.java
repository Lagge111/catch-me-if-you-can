package gamebar;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents a Gamebar button which lets the player return to the main menu
 * when in-game.
 */
public class GamebarButton extends StackPane {

	public GamebarButton(String s) {

		Rectangle bg = new Rectangle();
		bg.setWidth(100);
		bg.setHeight(30);
		bg.setFill(Color.rgb(255, 96, 96, 0.5));
		bg.setStroke(Color.rgb(200, 200, 200, 0.5));

		Text t = new Text(s);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Helvetica", 15));
		t.setStroke(Color.ANTIQUEWHITE);

		getChildren().addAll(bg, t);

		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.TRANSPARENT);
			}
		});

		t.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.TRANSPARENT);
			}
		});

		bg.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.rgb(255, 96, 96, 0.5));
			}

		});

		t.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.rgb(255, 96, 96, 0.5));
			}
		});
	}

}
