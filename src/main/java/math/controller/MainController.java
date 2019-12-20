package math.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.Scene;
import javafx.stage.Stage;
import math.model.Settings;
import math.model.Stats;
import math.ui.StartView;
import math.ui.StatsView;
import math.ui.TestView;

public class MainController {
	private static final String SETTINGS_FILE = "settings.json";
	private static final String STATS_FILE = "stats.json";

	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Stage stage;
	private final Settings settings;
	private final Stats stats;

	public MainController(Stage stage) {
		this.stage = stage;
		settings = loadFile(SETTINGS_FILE, Settings.class, Settings::new);
		stats = loadFile(STATS_FILE, Stats.class, Stats::new);
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	private <T> T loadFile(String fileName, Class<T> clazz, Supplier<T> defaultValue) {
		File file = getFile(fileName);
		try {
			if (file.exists()) {
				return objectMapper.readValue(file, clazz);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defaultValue.get();
	}

	private File getFile(String name) {
		File dir = new File(new File(System.getProperty("user.home")), "math");
		dir.mkdirs();
		return new File(dir, name);
	}

	public void saveSettings() {
		saveToFile(settings, SETTINGS_FILE);
	}

	public void saveStats() {
		saveToFile(stats, STATS_FILE);
	}

	private void saveToFile(Object object, String fileName) {
		try {
			File file = getFile(fileName);
			objectMapper.writeValue(file, object);
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

	public Stats getStats() {
		return stats;
	}

	public void startTest(Integer test) {
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

	public void shutdown() {
		executor.shutdown();
	}
}
