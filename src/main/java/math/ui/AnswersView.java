package math.ui;

import java.util.List;
import java.util.function.Consumer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class AnswersView extends GridPane implements AnswerSelector {

	public AnswersView(List<Integer> answers, Consumer<Integer> answerConsumer) {
		setAlignment(Pos.CENTER);
		setVgap(5);
		setHgap(5);
		int row = 0;
		int col = 0;
		if (answers.size() == 1) {
			Button btn = createButton(answerConsumer, answers.get(0));
			add(btn, 0, 0, 2, 1);
		} else {
			for (Integer answer : answers) {
				Button btn = createButton(answerConsumer, answer);
				add(btn, col, row);
				row++;
				if (row >= answers.size() / 2) {
					col++;
					row = 0;
				}
			}
		}
	}

	private Button createButton(Consumer<Integer> answerConsumer, Integer answer) {
		Button btn = new Button(String.valueOf(answer));
		btn.setFont(new Font(24));
		btn.setPrefSize(100, 100);
		btn.setOnAction((e) -> answerConsumer.accept(answer));
		return btn;
	}

	@Override
	public Region getAnswerRegion(int answer) {
		for (Object it : getChildren()) {
			if (it instanceof Button) {
				Button button = (Button) it;
				if (button.getText().equals(String.valueOf(answer))) {
					return button;
				}
			}
		}
		return null;
	}

}
