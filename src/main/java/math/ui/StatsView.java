package math.ui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import math.controller.MainController;

public class StatsView extends VBox {

	public StatsView(MainController mainController) {
		createStats(mainController);
		createOK(mainController);
		setAlignment(Pos.CENTER);
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
		for (int i = 0; i <= 10; i++) {
			String text = i > 0 ? String.valueOf(i - 1) : "avg";
			Label label = new Label(text);
			label.setPrefSize(50, 10);
			label.setPadding(new Insets(5));
			grid.add(label, col, i + 1);
		}

		col++;
		for (int test : mainController.getTests()) {
			for (int i = 0; i <= 11; i++) {
				Label label = new Label();
				if (i == 0) {
					label.setText(String.valueOf(test));
				} else if (i == 1) {
					setValue(label, mainController.getSettings().getStats().getScore(test));
				} else {
					setValue(label, mainController.getSettings().getStats().getScore(test, i - 2));
				}
				label.setPrefSize(50, 10);
				label.setPadding(new Insets(5));
				grid.add(label, col, i);
			}
			col++;
		}
	}

	private void setValue(Label label, Double score) {
		if (score != null) {
			int value = (int) Math.ceil(score * 10);
			label.setText(String.valueOf(value));
			label.setBackground(UI.background(UI.getColor(value)));
		}
	}

}
