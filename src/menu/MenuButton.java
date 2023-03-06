package menu;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents the visual effects of the menu buttons, e.g. when you hover over a
 * button it is highlighted.
 */
public class MenuButton extends StackPane {

	public MenuButton(String s) {

		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

		Rectangle bg = new Rectangle();
		bg.setEffect(ds);
		bg.setWidth(200);
		bg.setHeight(30);
		bg.setFill(Color.TRANSPARENT);
		bg.setStroke(Color.ANTIQUEWHITE);
		bg.setArcHeight(45);
		bg.setArcWidth(45);

		Text t = new Text(s);
		t.setEffect(ds);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		t.setStroke(Color.ANTIQUEWHITE);

		getChildren().addAll(bg, t);

		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.rgb(255, 96, 96, 0.5));
			}
		});

		t.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.rgb(255, 96, 96, 0.5));
			}
		});

		bg.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.TRANSPARENT);
			}

		});

		t.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				bg.setFill(Color.TRANSPARENT);
			}
		});
	}

}
