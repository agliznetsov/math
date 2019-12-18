package math;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FXDemo extends Application {

	Stage stage;

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		showStart();
		stage.setTitle("Quit button");
		stage.show();
	}

	private void showStart() {
		stage.setScene(getStartScene());
	}

	private void showPage() {
		stage.setScene(getPageScene());
	}

	private Scene getPageScene() {
		Button btn2 = new Button();
		btn2.setText("Back");
		btn2.setOnAction((event) -> showStart());

		HBox root = new HBox();
		root.setPadding(new Insets(25));
		root.getChildren().add(btn2);

		return new Scene(root, 280, 200);
	}

	private Scene getStartScene() {
		Button btn = new Button();
		btn.setText("Quit");
		btn.setOnAction((event) -> Platform.exit());

		Button btn2 = new Button();
		btn2.setText("Page2");
		btn2.setOnAction((event) -> showPage());

		HBox root = new HBox();
		root.setPadding(new Insets(25));
		root.getChildren().add(btn);
		root.getChildren().add(btn2);

		return new Scene(root, 280, 200);
	}

	public static void main(String[] args) {
		launch(args);
	}
}