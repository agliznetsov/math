package math.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class UI {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;

	public static Background background(Paint color) {
		return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}

	public static Paint getColor(int value) {
		if (value < 7) {
			return Color.RED;
		} else if (value < 9) {
			return Color.YELLOW;
		} else {
			return Color.LIGHTGREEN;
		}
	}

	public static Border border(Paint color) {
		return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	}
}
