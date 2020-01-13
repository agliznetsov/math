package math.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ButtonGroup<T> extends HBox {
    private final static String SELECTED = "-fx-border-color:red;-fx-border-width: 0 0 5 0;";

    private final Map<Object, Button> buttons = new HashMap<>();
    private double bWidth = 50;
    private double bHeight = 50;
    private double fontSize = 18;
    private T label;

    public ButtonGroup(double width, double height, double fontSize) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        this.bWidth = width;
        this.bHeight = height;
        this.fontSize = fontSize;
    }

    public void setLabels(Collection<T> labels) {
        buttons.clear();
        getChildren().clear();
        labels.forEach(it -> {
            Button button = createButton(it);
            buttons.put(it, button);
            getChildren().add(button);
        });
    }

    public void selectLabel(T label) {
        buttons.values().forEach(it -> activate(it, false));
        activate(buttons.get(label), true);
        this.label = label;
    }

    public T getLabel() {
        return label;
    }

    private void activate(Button button, boolean activated) {
        if (activated) {
            button.setStyle(SELECTED);
        } else {
            button.setStyle("");
        }
    }

    private Button createButton(T label) {
        Button btn = new Button(label.toString());
        btn.setFont(new Font(fontSize));
        btn.setPrefSize(bWidth, bHeight);
        btn.setOnAction((event) -> ButtonGroup.this.selectLabel(label));
        return btn;
    }
}
