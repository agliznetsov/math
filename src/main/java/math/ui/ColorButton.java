package math.ui;

import java.awt.*;

import javax.swing.*;

public class ColorButton extends JButton {
	boolean on = false;

	public ColorButton(String s) {
		super(s);
		setBackground(Color.white);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (getModel().isPressed()) {
			g.setColor(Color.black);
			g.drawRect(1, 1, getWidth() - 2, getHeight() - 2);
		}
		g.setColor(getBackground());
		if (this.on) {
			g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
		}
	}

	public void setOn(boolean on) {
		this.on = on;
		this.repaint();
	}
}
