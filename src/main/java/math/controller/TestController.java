package math.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import math.ui.TestView;

public class TestController {
	static List<Integer> allAnswers = new ArrayList<>();

	static {
		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				allAnswers.add(a * b);
			}
		}
	}

	Random random = new Random();
	MainController mainController;
	int test;
	TestView testView;
	int index = 0;
	List<Integer> questions = new ArrayList<>();
	List<Boolean> answers = new ArrayList<>();


	public TestController(MainController mainController) {
		this.mainController = mainController;
	}

	public void start(TestView testView, int test) {
		this.testView = testView;
		this.test = test;
		this.index = 0;
		this.questions.clear();
		this.answers.clear();
		for (int i = 0; i < 10; i++) {
			questions.add(i);
		}
		Collections.shuffle(questions);
		showQuestion();
	}

	private void showQuestion() {
		String question = questions.get(index) + " x " + test;

		List<Integer> questionAnswers = new ArrayList<>();
		questionAnswers.add(questions.get(index) * test);
		while (questionAnswers.size() < 4) {
			int a = allAnswers.get(random.nextInt(allAnswers.size()));
			if (!questionAnswers.contains(a)) {
				questionAnswers.add(a);
			}
		}
		Collections.shuffle(questionAnswers);

		testView.showQuestion(question, questionAnswers);
	}

	public void answer(int answer) {
		boolean correct = answer == questions.get(index) * test;
		if (answers.size() == index) {
			answers.add(correct);
		}
		Runnable callback = correct ? this::nextQuestion : null;
		testView.feedBack(answer, correct, callback);
	}

	private void nextQuestion() {
		index++;
		if (index < questions.size()) {
			showQuestion();
		} else {
			endTest();
		}
	}

	private void endTest() {
		int correctAnswers = 0;
		for (int i = 0; i < questions.size(); i++) {
			String key = questions.get(i) + "x" + test;
			mainController.getSettings().getStats().addScore(key, answers.get(i) ? 1 : 0);
			if (answers.get(i)) {
				correctAnswers++;
			}
		}
		mainController.getSettings().getStats().addScore(String.valueOf(test), correctAnswers * 1.0 / answers.size());
		mainController.saveSettings();
		testView.showResult(correctAnswers + " / " + answers.size());
	}

	public void showStart() {
		mainController.showStart();
	}
}
