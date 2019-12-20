package math.ui;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import math.controller.TestController;

public class TestView extends VBox {
	private GridPane grid;
	private final TestController testController;

	public TestView(TestController testController) {
		this.testController = testController;
		setAlignment(Pos.CENTER);
	}

	public void showQuestion(String question, List<Integer> answers) {
		getChildren().clear();

		Label label = new Label(question);
		label.setFont(new Font(32));
		label.setPadding(new Insets(10));
		this.getChildren().add(label);

		grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.CENTER);
		this.getChildren().add(grid);
		int row = 0;
		int col = 0;
		for (Integer answer : answers) {
			Button btn = new Button(String.valueOf(answer));
			btn.setFont(new Font(24));
			btn.setPrefSize(100, 100);
			btn.setOnAction((e) -> testController.answer(answer));
			grid.add(btn, col, row);
			col++;
			if (col >= answers.size() / 2) {
				row++;
				col = 0;
			}
		}
	}

	public void feedBack(int answer, boolean correct, Runnable callback) {
		for (Object it : grid.getChildren()) {
			if (it instanceof Button) {
				Button button = (Button) it;
				if (button.getText().equals(String.valueOf(answer))) {
					testController.getExecutor().submit(() -> animate(button, UI.background(correct ? Color.GREEN : Color.RED), callback));
				}
			}
		}
	}

	private void animate(Button button, Background background, Runnable callback) {
		Background original = button.getBackground();
		for (int i = 0; i < 3; i++) {
			button.setBackground(background);
			sleep(50);
			button.setBackground(original);
			sleep(50);
		}
		if (callback != null) {
			Platform.runLater(callback);
		}
	}

	private void sleep(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
		}
	}

	public void showResult(String text) {
		getChildren().clear();

		Label label = new Label(text);
		label.setPadding(new Insets(10));
		label.setFont(new Font(32));
		getChildren().add(label);

		Button btn = new Button("OK");
		btn.setOnAction((e) -> testController.showStart());
		getChildren().add(btn);
	}
}
