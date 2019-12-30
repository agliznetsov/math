package math.controller;

import java.util.concurrent.ExecutorService;

public interface QuizController {
    void start(Integer multiplier);

    void answer(int answer);

    void mainMenu();

    void nextQuestion();

    ExecutorService getExecutor();

    void restart();
}
