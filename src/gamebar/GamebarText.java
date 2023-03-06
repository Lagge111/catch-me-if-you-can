package gamebar;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents generic text used by other Gamebar classes.
 */
public class GamebarText extends Text {

	public GamebarText(String text, int size) {
		setText(text);
		setFill(Color.rgb(250, 250, 250, 0.9));
		setFont(Font.font("Helvetica", size));
	}
}
