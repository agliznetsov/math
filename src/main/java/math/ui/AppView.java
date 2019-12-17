package math.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.*;

public class AppView extends JFrame {
	JPanel buttonsPanel;
	JLabel statusLabel;
	MainController mainController;

	public AppView(MainController mainController) {
		super("Math");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainController = mainController;
		createControls();
	}

	private void createControls() {
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton settings = new JButton("Settings");
		settings.addActionListener((e) -> mainController.showSettings());
		statusPanel.add(settings);
		statusLabel = new JLabel();
		statusPanel.add(statusLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2));
		mainPanel.add(createButtonsPanel());


		root.add(statusPanel);
		root.add(mainPanel);

		add(root);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel createButtonsPanel() {
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

		return buttonsPanel;
	}


}
