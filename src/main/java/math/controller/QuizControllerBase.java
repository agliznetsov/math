package math.controller;

import math.model.Question;
import math.ui.QuizView;

import java.util.*;
import java.util.concurrent.ExecutorService;

public abstract class QuizControllerBase implements QuizController {
    protected static Map<Integer, List<Question>> allQuestions = new HashMap<>();

    static {
        for (int b = 0; b < 10; b++) {
            List<Question> list = new ArrayList<>();
            for (int a = 0; a < 10; a++) {
                list.add(new Question(a, b));
            }
            allQuestions.put(b, list);
        }
    }

    protected final Random random = new Random();
    protected final MainController mainController;
    protected final QuizView quizView;


    public QuizControllerBase(MainController mainController, QuizView quizView) {
        this.mainController = mainController;
        this.quizView = quizView;
    }

    @Override
    public void mainMenu() {
        mainController.showStart();
    }

    @Override
    public ExecutorService getExecutor() {
        return mainController.getExecutor();
    }

    protected void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
