package math.ui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import math.controller.MainController;
import math.model.Question;

public class StatsView extends VBox {

	public StatsView(MainController mainController) {
		createStats(mainController);
		createOK(mainController);
		setAlignment(Pos.CENTER);
		setSpacing(10);
	}

	private void createOK(MainController mainController) {
		Button btn = new Button();
		btn.setText("OK");
		btn.setPrefSize(50, 10);
		btn.setOnAction((event) -> mainController.showStart());
		btn.setAlignment(Pos.CENTER);
		this.getChildren().add(btn);
	}

	private void createStats(MainController mainController) {
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		this.getChildren().add(grid);

		int col = 0;
		for (int i = 0; i < 10; i++) {
			String text = String.valueOf(i);
			Label label = new Label(text);
			label.setPrefSize(50, 10);
			label.setPadding(new Insets(5));
			grid.add(label, col, i + 1);
		}

		col++;
		for (int multiplier = 0; multiplier < 10; multiplier++) {
			for (int i = 0; i <= 10; i++) {
				Label label = new Label();
				if (i == 0) {
					label.setText(String.valueOf(multiplier));
				} else {
					setValue(label, mainController.getStats().getScore(new Question(i - 1, multiplier).key()));
				}
				label.setPrefSize(50, 10);
				label.setPadding(new Insets(5));
				grid.add(label, col, i);
			}
			col++;
		}
	}

	private void setValue(Label label, int score) {
		if (score > 0) {
			int value = score;
			label.setText(String.valueOf(value));
			label.setBackground(UI.background(UI.getColor(value)));
		}
	}

}
