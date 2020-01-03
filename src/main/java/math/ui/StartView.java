package math.ui;


import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import math.controller.MainController;
import math.model.LearnLevel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StartView extends VBox {
    private static final String ALL = "0..9";
    ComboBox comboBox;
    MainController mainController;

    public StartView(MainController mainController) {
        this.mainController = mainController;
        setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        this.getChildren().add(grid);

        comboBox = new ComboBox(FXCollections.observableArrayList(getMultipliers()));
        comboBox.setPrefSize(310, 50);
        comboBox.getSelectionModel().select(0);
        grid.add(comboBox, 0, 0, 3, 1);

        grid.add(createLearnButton(), 0, 1);
        grid.add(createButton("Test", () -> mainController.startTest(getMultiplier())), 1, 1);
        grid.add(createButton("Stats", mainController::showStats), 2, 1);
    }

    private Integer getMultiplier() {
        int i = comboBox.getSelectionModel().getSelectedIndex();
        if (i < 10) {
            return i;
        } else {
            return null;
        }
    }

    private Button createButton(String text, Runnable onAction) {
        Button btn = new Button(text);
        btn.setFont(new Font(24));
        btn.setPrefSize(100, 50);
        btn.setOnAction((event) -> onAction.run());
        return btn;
    }

    private MenuButton createLearnButton() {
        MenuButton menuButton = new MenuButton("Learn");
        MenuItem item = new MenuItem("Continue");
        item.setOnAction((e) -> learn(null));
        menuButton.getItems().add(item);
        for(LearnLevel level : LearnLevel.values()) {
            item = new MenuItem(level.name());
            item.setOnAction((e) -> learn(level));
            menuButton.getItems().add(item);
        }
        menuButton.setFont(new Font(18));
        menuButton.setPrefSize(100, 50);
        return menuButton;
    }

    private void learn(LearnLevel level) {
        Integer multiplier = getMultiplier();
        if (multiplier != null) {
            mainController.learn(getMultiplier(),level);
        }
    }

    private Collection<String> getMultipliers() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        list.add(ALL);
        return list;
    }

}
