package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Dot extends JComponent {

	public Dot(int x, int y) {
		setLocation(x, y);
		setSize(32, 32);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fill(new Ellipse2D.Double(getX() + 12, getY() + 12, 7, 7));
	}
	
}
