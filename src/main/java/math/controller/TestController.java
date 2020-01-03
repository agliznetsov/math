package math.controller;

import javafx.application.Platform;
import math.model.Question;
import math.ui.QuizView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestController extends QuizControllerBase {
    Integer multiplier;
    int index;
    int correctAnswers;
    long start;
    long totalTime;
    List<Question> questions;
    final AtomicInteger time = new AtomicInteger();
    int timePerQuestion;

    public TestController(MainController mainController, QuizView quizView) {
        super(mainController, quizView);
    }

    public void start(Integer multiplier, int time) {
        this.multiplier = multiplier;
        this.timePerQuestion = time * 1000;
        restart();
    }

    public void restart() {
        this.index = 0;
        this.totalTime = 0;
        this.correctAnswers = 0;
        this.questions = new ArrayList<>();
        if (multiplier != null) {
            questions.addAll(allQuestions.get(multiplier));
        } else {
            for (int i = 0; i < 10; i++) {
                questions.add(allQuestions.get(i).get(random.nextInt(10)));
            }
        }
        Collections.shuffle(questions);
        showQuestion();
        mainController.getExecutor().submit(this::timer);
    }

    private void showQuestion() {
        Question question = questions.get(index);
        quizView.showQuestion(question.toString(), null);
        quizView.showStatus((index + 1) + " / " + questions.size());
        start = System.currentTimeMillis();
        time.set(timePerQuestion);
        showTime(time.get());
    }

    private void timer() {
        while (index < questions.size()) {
            sleep(50);
            int value = time.addAndGet(-50);
            if (index < questions.size()) {
                if (value > 0) {
                    showTime(value);
                } else {
                    Platform.runLater(this::nextQuestion);
                }
            }
        }
    }

    private void showTime(int value) {
        quizView.showTime(1.0 - (value * 1.0 / timePerQuestion));
    }

    public void answer(int answer) {
        Question question = questions.get(index);
        long end = System.currentTimeMillis();
        totalTime += (end - start);
        boolean correct = answer == question.answer();
        if (correct) {
            correctAnswers++;
            mainController.getStats().incrementScore(question.key());
        } else {
            mainController.getStats().decrementScore(question.key());
        }
        mainController.saveStats();
        quizView.feedBack(answer, correct, this::nextQuestion);
    }

    public void nextQuestion() {
        index++;
        if (index < questions.size()) {
            showQuestion();
        } else {
            endTest();
        }
    }

    private void endTest() {
        quizView.showResult(correctAnswers + " / " + questions.size(), totalTime);
    }

}
