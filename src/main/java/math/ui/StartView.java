package math.ui;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import math.controller.MainController;
import math.model.LearnLevel;
import math.model.Multiplier;
import math.model.Operation;
import math.model.Settings;

import java.util.Arrays;

public class StartView extends VBox {
    MainController mainController;
    ButtonGroup<Operation> op;
    ButtonGroup<Multiplier> multiplier;
    ButtonGroup<LearnLevel> learnLevel;
    ButtonGroup<Integer> time;

    public StartView(MainController mainController) {
        this.mainController = mainController;
        setAlignment(Pos.CENTER);
        setSpacing(5);

        op = new ButtonGroup<>(215, 50, 28);
        op.setLabels(Arrays.asList(Operation.values()));

        multiplier = new ButtonGroup<>(35, 50, 18);
        multiplier.setLabels(Arrays.asList(Multiplier.values()));

        learnLevel = new ButtonGroup<>(105, 50, 18);
        learnLevel.setLabels(Arrays.asList(LearnLevel.values()));

        time = new ButtonGroup<>(68, 50, 18);
        time.setLabels(Arrays.asList(5, 10, 15, 20, 25, 30));

        addRow(op);
        addRow(multiplier);
        addRow(learnLevel);
        addRow(time);
        addRow(createButton("Learn", this::learn),
                createButton("Test", this::test),
                createButton("Stats", this::stats)
        );

        loadSettings();
    }

    private void loadSettings() {
        Settings settings = mainController.getSettings();
        op.selectLabel(Operation.valueOf(settings.getOp()));
        multiplier.selectLabel(Multiplier.valueOf(settings.getMultiplier()));
        learnLevel.selectLabel(LearnLevel.valueOf(settings.getLearnLevel()));
        time.selectLabel(settings.getTime());
    }

    private void updateSettings() {
        Settings settings = mainController.getSettings();
        settings.setOp(op.getValue().name());
        settings.setLearnLevel(learnLevel.getValue().name());
        settings.setMultiplier(multiplier.getValue().name());
        settings.setTime(time.getValue());
        mainController.saveSettings();
    }

    private void test() {
        updateSettings();
        mainController.startTest(op.getValue(), multiplier.getValue(), time.getValue());
    }

    private void learn() {
        updateSettings();
        mainController.learn(op.getValue(), multiplier.getValue(), learnLevel.getValue());
    }

    private void stats() {
        mainController.showStats(op.getValue());
    }

    private void addRow(Node... controls) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().addAll(controls);
        this.getChildren().add(hBox);
    }

    private Button createButton(String text, Runnable onAction) {
        Button btn = new Button(text);
        btn.setFont(new Font(18));
        btn.setPrefSize(140, 50);
        btn.setOnAction((event) -> onAction.run());
        return btn;
    }
}
