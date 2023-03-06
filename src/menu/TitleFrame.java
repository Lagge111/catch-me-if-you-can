package menu;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a visual frame around the title on the main menu.
 */
public class TitleFrame extends Rectangle {

	public TitleFrame() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		setEffect(ds);
		setWidth(580);
		setHeight(60);
		setFill(Color.TRANSPARENT);
		setStroke(Color.ANTIQUEWHITE);
	}

}
