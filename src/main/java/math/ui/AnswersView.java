package math.ui;

import java.util.List;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import math.controller.TestController;

public class AnswersView extends GridPane implements AnswerSelector {

	public AnswersView(List<Integer> answers, Consumer<Integer> answerConsumer) {
		setAlignment(Pos.CENTER);
		setVgap(5);
		setHgap(5);
		int row = 0;
		int col = 0;
		for (Integer answer : answers) {
			Button btn = new Button(String.valueOf(answer));
			btn.setFont(new Font(24));
			btn.setPrefSize(100, 100);
			btn.setOnAction((e) -> answerConsumer.accept(answer));
			add(btn, col, row);
			col++;
			if (col >= answers.size() / 2) {
				row++;
				col = 0;
			}
		}
	}

	@Override
	public Region getAnswerRegion(int answer) {
		for (Object it : getChildren()) {
			if (it instanceof Button) {
				Button button = (Button) it;
				if (button.getText().equals(String.valueOf(answer))) {
					return button;
				}
			}
		}
		return null;
	}

}
