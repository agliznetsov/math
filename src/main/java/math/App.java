package math;


import javafx.application.Application;
import javafx.stage.Stage;
import math.controller.MainController;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MainController controller = new MainController(stage);
		controller.start();
		stage.setOnCloseRequest(e -> {
			controller.shutdown();
		});
	}

}