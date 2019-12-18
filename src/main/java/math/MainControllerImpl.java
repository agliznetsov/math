package math;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import math.ui.MainController;
import math.ui.AppView;
import math.ui.StartView;
import math.ui.StatsView;

public class MainControllerImpl implements MainController {
	ObjectMapper objectMapper = new ObjectMapper();
	AppView appView;
	Settings settings;

	public MainControllerImpl() {
		loadSettings();
		this.appView = new AppView(this);
	}

	private void loadSettings() {
		settings = new Settings();
		try {
			File file = getPropertiesFile();
			if (file.exists()) {
				objectMapper.readValue(file, Settings.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File getPropertiesFile() {
		return new File(new File(System.getProperty("user.home")), "math.json");
	}

	@Override
	public void saveSettings() {
		try {
			File file = getPropertiesFile();
			objectMapper.writeValue(file, settings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		appView.setVisible(true);
		showStart();
	}

	@Override
	public Settings getSettings() {
		return settings;
	}

	@Override
	public List<Integer> getTests() {
		return Arrays.asList(2,3,4,5,6,7,8,9);
	}

	@Override
	public void startTest(int test) {

	}

	@Override
	public void showStats() {
		appView.setComponent(new StatsView(this));
	}

	@Override
	public void showStart() {
		appView.setComponent(new StartView(this));
	}

}
