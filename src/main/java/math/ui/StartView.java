package math.ui;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import math.controller.MainController;

public class StartView extends VBox {

	public StartView(MainController mainController) {
		setAlignment(Pos.CENTER);
		createTests(mainController);
	}

	private void createTests(MainController mainController) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		this.getChildren().add(grid);

		int row = 0;
		int col = 0;
		for (int i = 0; i < 10; i++) {
			int test = i;
			Button btn = new Button();
			btn.setFont(new Font(24));
			btn.setPrefSize(50, 50);
			btn.setText(String.valueOf(test));
			btn.setOnAction((e) -> mainController.startTest(test));
			grid.add(btn, col++, row);
			if (col == 5) {
				col = 0;
				row++;
			}
		}

		Button btn = new Button();
		btn.setText("0..9");
		btn.setFont(new Font(24));
		btn.setPrefSize(100, 50);
		btn.setOnAction((event) -> mainController.startTest(null));
		grid.add(btn, 0, row + 2, 2, 1);

		btn = new Button();
		btn.setText("Stats");
		btn.setFont(new Font(24));
		btn.setPrefSize(150, 50);
		btn.setOnAction((event) -> mainController.showStats());
		grid.add(btn, 2, row + 2, 3, 1);
	}

}
