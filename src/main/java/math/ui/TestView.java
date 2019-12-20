package math.ui;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import math.controller.TestController;

public class TestView extends VBox {
	private AnswerSelector answerSelector;
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

		if (answers != null) {
			answerSelector = new AnswersView(answers, testController::answer);
		} else {
			answerSelector = new CalculatorView(testController::answer);
		}
		this.getChildren().add((Node) answerSelector);
	}

	public void feedBack(int answer, boolean correct, Runnable callback) {
		Region region = answerSelector.getAnswerRegion(answer);
		testController.getExecutor().submit(() -> animate(region, UI.background(correct ? Color.GREEN : Color.RED), callback));
	}

	private void animate(Region button, Background background, Runnable callback) {
		Node node = (Node) answerSelector;
		node.setDisable(true);
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
		node.setDisable(false);
	}

	private void sleep(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
		}
	}

	public void showResult(String text, long totalTime) {
		getChildren().clear();

		getChildren().add(createLabel(text));
		getChildren().add(createLabel(formatTime(totalTime)));

		Button btn = new Button("OK");
		btn.setOnAction((e) -> testController.showStart());
		getChildren().add(btn);
	}

	protected static String formatTime(long totalTime) {
		int time = (int) (totalTime / 1_000);
		int min = time / 60;
		int sec = time % 60;
		return String.format("%d:%02d", min, sec);
	}

	private Label createLabel(String text) {
		Label label = new Label(text);
		label.setPadding(new Insets(10));
		label.setFont(new Font(32));
		return label;
	}
}
