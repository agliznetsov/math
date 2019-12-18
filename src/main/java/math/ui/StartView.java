package math.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class StartView extends JPanel {

	public StartView(MainController mainController) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(createTests(mainController));
		add(createStats(mainController));
	}

	private Component createStats(MainController mainController) {
		JButton button = new JButton("Stats");
		button.setPreferredSize(new Dimension(50, 50));
		button.addActionListener(e -> mainController.showStats());
		return button;
	}

	private Component createTests(MainController mainController) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 4));
		panel.setPreferredSize(new Dimension(100, 50));

		for(int test : mainController.getTests()) {
			JButton button = new JButton(String.valueOf(test));
			button.setPreferredSize(new Dimension(50, 50));
			button.addActionListener(e -> mainController.startTest(test));
			panel.add(button);
		}

		return panel;
	}

}
