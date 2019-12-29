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
import math.controller.QuizController;
import math.controller.QuizControllerBase;

public class QuizView extends VBox {
	private AnswerSelector answerSelector;
	private QuizController quizController;

	public QuizView() {
		setAlignment(Pos.CENTER);
	}

	public void setQuizController(QuizController quizController) {
		this.quizController = quizController;
	}

	public void showQuestion(String question, List<Integer> answers) {
		getChildren().clear();
		getChildren().add(createLabel(question));

		if (answers != null) {
			answerSelector = new AnswersView(answers, quizController::answer);
		} else {
			answerSelector = new CalculatorView(quizController::answer);
		}
		this.getChildren().add((Node) answerSelector);
	}

	public void showAnswer(String question, String answer) {
		getChildren().clear();
		getChildren().add(createLabel(question + " = " + answer));
		getChildren().add(createOKButton());
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

	public void showResult(String text, long totalTime) {
		getChildren().clear();

		getChildren().add(createLabel(text));
		getChildren().add(createLabel(formatTime(totalTime)));

		Button btn = new Button("OK");
		btn.setOnAction((e) -> quizController.showStart());
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

	private Button createOKButton() {
		Button btn = new Button("OK");
		btn.setFont(new Font(24));
		btn.setPrefSize(100, 50);
		btn.setOnAction((e) -> quizController.nextQuestion());
		return btn;
	}
}
