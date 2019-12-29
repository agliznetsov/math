package math.controller;

import java.util.concurrent.ExecutorService;

public interface QuizController {
    void start(Integer multiplier);

    void answer(int answer);

    void showStart();

    void nextQuestion();

    ExecutorService getExecutor();
}
