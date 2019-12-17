package math.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class StartView extends JPanel {

	ColorButton button;
	JCheckBox checkBox;
	JLabel label0;
	JLabel label1;
	JLabel label2;

	public StartView(MainController mainController) {
		button = new ColorButton(" " + id);
		button.setPreferredSize(new Dimension(50, 50));
		button.setBackground(Color.lightGray);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel label = new JLabel("#" + id, JLabel.CENTER);
		label.setForeground(Color.lightGray);
		label.setPreferredSize(new Dimension(30, 50));
		checkBox = new JCheckBox();
		checkBox.setPreferredSize(new Dimension(20, 50));
		add(label);
		add(checkBox);
		add(button);
		add(createLabels());

		setController(mainController);
	}

	public String getId() {
		return id;
	}

	private Component createLabels() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(new GridLayout(3, 1));
		panel.setPreferredSize(new Dimension(100, 50));

		label0 = new JLabel("", JLabel.CENTER);
		label0.setForeground(Color.black);
		label0.setBackground(Color.white);

		label1 = new JLabel("", JLabel.CENTER);
		label1.setForeground(Color.white);

		label2 = new JLabel("", JLabel.CENTER);
		label2.setForeground(Color.white);

		panel.add(label0);
		panel.add(label1);
		panel.add(label2);

		return panel;
	}

	public void setController(MainController mainController) {
		button.addMouseListener(new MouseAdapter() {
		});
		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
