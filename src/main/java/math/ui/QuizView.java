package math.ui;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import math.controller.QuizController;

public class QuizView extends AnchorPane {
	private AnswerSelector answerSelector;
	private QuizController quizController;

	private VBox mainPane;
	private HBox bottomPane;

	public QuizView() {
		mainPane = new VBox();
		bottomPane = new HBox();
		mainPane.setAlignment(Pos.CENTER);
		getChildren().addAll(mainPane, bottomPane);

		setTopAnchor(mainPane, 0.0);
		setLeftAnchor(mainPane, 0.0);
		setRightAnchor(mainPane, 0.0);
		setBottomAnchor(mainPane, 50.0);

		setBottomAnchor(bottomPane, 5.0);
		setRightAnchor(bottomPane, 5.0);
	}

	public void setQuizController(QuizController quizController) {
		this.quizController = quizController;
	}

	public void showStatus(String text) {
		bottomPane.getChildren().clear();
		bottomPane.getChildren().add(new Label(text));
	}

	public void showQuestion(String question, List<Integer> answers) {
		mainPane.getChildren().clear();
		mainPane.getChildren().add(createLabel(question));

		if (answers != null) {
			answerSelector = new AnswersView(answers, quizController::answer);
		} else {
			answerSelector = new CalculatorView(quizController::answer);
		}
		mainPane.getChildren().add((Node) answerSelector);
	}

	public void showAnswer(String question, String answer) {
		mainPane.getChildren().clear();
		mainPane.getChildren().add(createLabel(question + " = " + answer));
		mainPane.getChildren().add(createButton("OK", quizController::nextQuestion));
	}

	public void feedBack(int answer, boolean correct, Runnable callback) {
		Region region = answerSelector.getAnswerRegion(answer);
		quizController.getExecutor().submit(() -> animate(region, UI.background(correct ? Color.GREEN : Color.RED), callback));
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

	public void showResult(String text, Long totalTime) {
		mainPane.getChildren().clear();
		mainPane.getChildren().add(createLabel(text));
		if (totalTime != null) {
			mainPane.getChildren().add(createLabel(formatTime(totalTime)));
		}
		mainPane.getChildren().add(createButton("Close", quizController::mainMenu));
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

	private Button createButton(String text, Runnable action) {
		Button btn = new Button(text);
		btn.setFont(new Font(18));
		btn.setPrefSize(100, 50);
		btn.setOnAction((e) -> action.run());
		return btn;
	}
}
