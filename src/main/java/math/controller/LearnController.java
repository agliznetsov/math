package math.controller;

import math.model.LearnLevel;
import math.model.Multiplier;
import math.model.Operation;
import math.model.Question;
import math.ui.QuizView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LearnController extends QuizControllerBase {
	Operation op;
	Integer multiplier;
    int index;
    List<Question> questions;
    List<String> keys;
	final List<LearnLevel> levels = Arrays.asList(LearnLevel.values());

    public LearnController(MainController mainController, QuizView quizView) {
		super(mainController, quizView);
	}

	public void start(Operation op, Multiplier m, LearnLevel level) {
    	this.op = op;
    	this.multiplier = m.getValue();
		index = 0;
		questions = new ArrayList<>(allQuestions.get(this.op).get(this.multiplier));
		keys = questions.stream().map(Question::key).collect(Collectors.toList());
		if (level != null) {
			keys.forEach(it -> mainController.getStats().setScore(it, level.getStartStep()));
		}
		Collections.shuffle(questions);
		nextQuestion();
	}

	private void showQuestion() {
		Question question = questions.get(index);
		int score = mainController.getStats().getScore(question.key());
		List<Integer> questionAnswers = getAnswers(getAnswerCount(score));
		quizView.showQuestion(question.toString(), questionAnswers);
	}

	private int getAnswerCount(int score) {
		LearnLevel level = levels.stream().filter(it -> it.getStartStep() <= score && it.getEndStep() >= score).findAny().get();
		return level.getAnswerCount();
	}

	private List<Integer> getAnswers(int count) {
    	if (count == 0) {
    		return null;
		}
		List<Integer> questionAnswers;
		questionAnswers = new ArrayList<>();
		questionAnswers.add(questions.get(index).answer());
		while (questionAnswers.size() < count) {
			int a = allQuestions.get(op).get(random.nextInt(10)).get(random.nextInt(10)).answer();
			if (!questionAnswers.contains(a)) {
				questionAnswers.add(a);
			}
		}
		Collections.shuffle(questionAnswers);
		return questionAnswers;
	}

	public void answer(int answer) {
        Question question = questions.get(index);
		boolean correct = answer == question.answer();
		if (correct) {
            mainController.getStats().incrementScore(question.key());
            mainController.saveStats();
			quizView.feedBack(answer, true, this::nextQuestion);
        } else {
		    mainController.getStats().decrementScore(question.key());
		    mainController.saveStats();
			quizView.feedBack(answer, false, () -> quizView.showAnswer(question.toString(), String.valueOf(question.answer())));
        }
	}

	public void nextQuestion() {
		showStatus();
        questions = questions.stream().filter(it -> mainController.getStats().getScore(it.key()) < 9).collect(Collectors.toList());
        if (questions.isEmpty()) {
            endTest();
        } else {
            index++;
            if (index >= questions.size()) {
                index = 0;
            }
            showQuestion();
		}
	}

	private void showStatus() {
    	int sum = keys.stream().mapToInt(it -> mainController.getStats().getScore(it)).sum();
    	int percent = (int) (sum / 90.0 * 100);
		quizView.showStatus(percent + "%");
	}

	private void endTest() {
		quizView.showResult("Done!", null);
	}
}
