package math.ui;

import java.awt.*;

import javax.swing.*;

public class StatsView extends JPanel {

	public StatsView(MainController mainController) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(createStats(mainController));
		add(createOK(mainController));
	}

	private Component createOK(MainController mainController) {
		JButton button = new JButton("OK");
		button.setPreferredSize(new Dimension(50, 50));
		button.addActionListener(e -> mainController.showStart());
		return button;
	}

	private Component createStats(MainController mainController) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(12, 12));

		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 12; col++) {
				String text = "col" + col + "row" + row;
				JLabel label = new JLabel(text);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(label);
			}
		}

		return panel;
	}

}
