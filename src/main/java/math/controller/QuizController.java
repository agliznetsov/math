package math.controller;

import java.util.concurrent.ExecutorService;

public interface QuizController {
    void answer(int answer);

    void mainMenu();

    void nextQuestion();

    ExecutorService getExecutor();
}
