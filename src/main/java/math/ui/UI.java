package math.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class UI {
	public static Background background(Paint color) {
		return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}

	public static Paint getColor(int value) {
		if (value < 6) {
			return Color.RED;
		} else if (value < 10 ) {
			return Color.YELLOW;
		} else {
			return Color.LIGHTGREEN;
		}
	}
}
