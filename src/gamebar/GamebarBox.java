package gamebar;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a generic Gamebar box, which surrounds the different visual
 * elements in the Gamebar.
 */
public class GamebarBox extends Rectangle {

	public GamebarBox() {
		setWidth(140);
		setHeight(30);
		setFill(Color.rgb(80, 80, 80, 0.5));
		setStroke(Color.rgb(200, 200, 200, 0.5));
	}
}
