package math;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import math.ui.MainController;
import math.ui.AppView;
import math.ui.SettingsDialog;

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
		return new File(new File(System.getProperty("user.home")), "math.properties");
	}

	private void saveSettings() {
		try {
			File file = getPropertiesFile();
			objectMapper.writeValue(file, settings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		appView.setVisible(true);
	}

	@Override
	public void showSettings() {
		SettingsDialog settingsView = new SettingsDialog(appView, this);
		settingsView.setVisible(true);
	}

	@Override
	public Settings getSettings() {
		return settings;
	}

	@Override
	public void applySettings() {
		saveSettings();
		//EventQueue.invokeLater(() -> mainView.createButtons(settings.getButtonsCount()));
	}

}
