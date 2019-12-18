package math.ui;

import java.util.List;

import math.Settings;

public interface MainController {

	Settings getSettings();

	void saveSettings();

	List<Integer> getTests();

	void startTest(int test);

	void showStats();

	void showStart();
}
