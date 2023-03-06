package menu;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents generic text (font, size, style etc.) used by the other menu
 * classes.
 */
public class MenuText extends Text {

	public MenuText(String text, int size) {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		setText(text);
		setEffect(ds);
		setFill(Color.WHITE);
		setFont(Font.font("Helvetica", FontWeight.BOLD, size));
		setStroke(Color.ANTIQUEWHITE);
	}

}
