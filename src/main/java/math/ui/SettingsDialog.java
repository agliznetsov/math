//package math.ui;
//
//import java.awt.*;
//
//import javax.swing.*;
//
//import math.Settings;
//
//public class SettingsDialog extends JDialog {
//
//	JTextField hostField;
//	JTextField portField;
//	JTextField buttonsField;
//	JTextField serialField;
//	Settings settings;
//	MainController mainController;
//
//	public SettingsDialog(Frame owner, MainController mainController) {
//		super(owner, "Settings", true);
//		this.mainController = mainController;
//		this.settings = mainController.getSettings();
//		createControls();
//		setLocationRelativeTo(owner);
//	}
//
//
//	private void createControls() {
//		JPanel root = new JPanel();
//		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
//
//		hostField = new JTextField(settings.getHost());
//		root.add(createPanel("Host", hostField));
//		portField = new JTextField(String.valueOf(settings.getPort()));
//		root.add(createPanel("Port", portField));
//		buttonsField = new JTextField(String.valueOf(settings.getButtonsCount()));
//		root.add(createPanel("# of buttons", buttonsField));
//		serialField = new JTextField(settings.getSerial());
//		root.add(createPanel("Serial #", serialField));
//
//		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JButton close = new JButton("OK");
//		close.addActionListener(e -> this.applyChanges());
//		panel.add(close);
//		root.add(panel);
//
//
//		add(root);
//		pack();
//		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//	}
//
//	private void applyChanges() {
//		settings.setHost(hostField.getText());
//		settings.setPort(Integer.parseInt(portField.getText()));
//		settings.setButtonsCount(Integer.parseInt(buttonsField.getText()));
//		settings.setSerial(serialField.getText());
//		this.setVisible(false);
//		mainController.applySettings();
//	}
//
//	private Component createPanel(String text, JTextField textField) {
//		JPanel panel = new JPanel(new FlowLayout());
//
//		JLabel label = new JLabel(text);
//		label.setPreferredSize(new Dimension(150, 30));
//		panel.add(label);
//
//		textField.setPreferredSize(new Dimension(150, 30));
//		panel.add(textField);
//
//		return panel;
//	}
//
//}
