package math.ui;

import java.util.function.Consumer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CalculatorView extends GridPane implements AnswerSelector {
	private Label label;
	private Consumer<Integer> answerConsumer;

	public CalculatorView(Consumer<Integer> answerConsumer) {
		this.answerConsumer = answerConsumer;

		setAlignment(Pos.CENTER);

		label = new Label("0");
		label.setFont(new Font(24));
		label.setPrefSize(150, 50);
		label.setBorder(UI.border(Color.BLACK));
		label.setAlignment(Pos.BASELINE_RIGHT);
		add(label, 0, 0, 3, 1);

		int row = 3;
		int col = 0;
		for (int i = 1; i < 10; i++) {
			int d = i;
			add(createButton(String.valueOf(i), () -> addDigit(d)), col, row);
			col++;
			if (col > 2) {
				row--;
				col = 0;
			}
		}
		add(createButton("0", () -> addDigit(0)), 0, 4);
		add(createButton("C", this::clear), 1, 4);
		add(createButton("OK", this::ok), 2, 4);
	}

	private void addDigit(int d) {
		if (label.getText().length() < 2) {
			if (label.getText().equals("0")) {
				label.setText(String.valueOf(d));
			} else {
				label.setText(label.getText() + d);
			}
		}
	}

	private void clear() {
		label.setText("0");
	}

	private void ok() {
		if (label.getText().length() > 0) {
			int answer = Integer.parseInt(label.getText());
			answerConsumer.accept(answer);
		}
	}

	private Button createButton(String text, Runnable action) {
		Button btn = new Button(text);
		btn.setFont(new Font(18));
		btn.setPrefSize(50, 50);
		btn.setOnAction((e) -> action.run());
		return btn;
	}

	@Override
	public Region getAnswerRegion(int answer) {
		return label;
	}
}
