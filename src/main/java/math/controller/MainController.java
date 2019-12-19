package math.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.Scene;
import javafx.stage.Stage;
import math.model.Settings;
import math.ui.StartView;
import math.ui.StatsView;
import math.ui.TestView;

public class MainController {
	ObjectMapper objectMapper = new ObjectMapper();
	Stage stage;
	Settings settings;

	public MainController(Stage stage) {
		loadSettings();
		this.stage = stage;
	}

	private void loadSettings() {
		try {
			File file = getPropertiesFile();
			if (file.exists()) {
				settings = objectMapper.readValue(file, Settings.class);
			} else {
				settings = new Settings();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File getPropertiesFile() {
		return new File(new File(System.getProperty("user.home")), "math.json");
	}

	public void saveSettings() {
		try {
			File file = getPropertiesFile();
			objectMapper.writeValue(file, settings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		showStart();
		stage.setTitle("Math");
		stage.show();
	}

	public Settings getSettings() {
		return settings;
	}

	public List<Integer> getTests() {
		return Arrays.asList(2,3,4,5,6,7,8,9);
	}

	public void startTest(int test) {
		TestController testController = new TestController(this);
		TestView testView = new TestView(testController);
		testController.start(testView, test);
		stage.setScene(new Scene(testView, 500, 500));
	}

	public void showStats() {
		stage.setScene(new Scene(new StatsView(this), 500, 500));
	}

	
	public void showStart() {
		stage.setScene(new Scene(new StartView(this), 500, 500));
	}

}
