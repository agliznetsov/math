package math.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ButtonWithLabel extends JPanel {
	private final String id;

	ColorButton button;
	JCheckBox checkBox;
	JLabel label0;
	JLabel label1;
	JLabel label2;

	public ButtonWithLabel(String id, MainController mainController) {
		this.id = id;
		setBackground(Color.darkGray);

		button = new ColorButton(" " + id);
		button.setPreferredSize(new Dimension(50, 50));
		button.setBackground(Color.lightGray);

		//		setLayout(new GridLayout(1, 2));
		setLayout(new FlowLayout());
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
