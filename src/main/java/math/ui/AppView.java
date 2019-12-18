package math.ui;

import java.awt.*;

import javax.swing.*;

public class AppView extends JFrame {
	MainController mainController;

	public AppView(MainController mainController) {
		super("Math");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainController = mainController;
//		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		setSize(600, 600);


	}

	public void setComponent(Component component) {
		//removeAll();

//		component.setBounds(100, 100, 500, 500);
//		component.setBackground(Color.BLACK);
//		add(component);
		JPanel panel = new StartView(mainController);
		panel.setBounds(50,50, 500, 500);

		JButton button = new JButton("test");
		button.setBounds(10, 10, 100, 50);
		panel.add(button);

		add(panel);
	}
}
