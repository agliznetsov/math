package math.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import math.model.Question;
import math.ui.TestView;

public class TestController {
	static Map<Integer,List<Question>> allQuestions = new HashMap<>();

	static {
		for (int b = 0; b < 10; b++) {
			List<Question> list = new ArrayList<>();
			for (int a = 0; a < 10; a++) {
				list.add(new Question(a, b));
			}
			allQuestions.put(b, list);
		}
	}

	Random random = new Random();
	MainController mainController;
	TestView testView;
	int index = 0;
	List<Question> questions = new ArrayList<>();
	List<Boolean> answers = new ArrayList<>();


	public TestController(MainController mainController) {
		this.mainController = mainController;
	}

	public void start(TestView testView, Integer multiplier) {
		this.testView = testView;
		this.index = 0;
		this.questions.clear();
		this.answers.clear();
		if (multiplier != null) {
			questions.addAll(allQuestions.get(multiplier));
		} else {
			for (int i = 0; i < 10; i++) {
				questions.add(allQuestions.get(i).get(random.nextInt(10)));
			}
		}
		Collections.shuffle(questions);
		showQuestion();
	}

	private void showQuestion() {
		List<Integer> questionAnswers = new ArrayList<>();
		questionAnswers.add(questions.get(index).answer());
		while (questionAnswers.size() < 4) {
			int a = allQuestions.get(random.nextInt(10)).get(random.nextInt(10)).answer();
			if (!questionAnswers.contains(a)) {
				questionAnswers.add(a);
			}
		}
		Collections.shuffle(questionAnswers);

		testView.showQuestion(questions.get(index).toString(), questionAnswers);
	}

	public void answer(int answer) {
		boolean correct = answer == questions.get(index).answer();
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
			String key = questions.get(i).key();
			mainController.getStats().addScore(key, answers.get(i) ? 1 : 0);
			if (answers.get(i)) {
				correctAnswers++;
			}
		}
		mainController.saveStats();
		testView.showResult(correctAnswers + " / " + answers.size());
	}

	public void showStart() {
		mainController.showStart();
	}

	public ExecutorService getExecutor() {
		return mainController.getExecutor();
	}
}
