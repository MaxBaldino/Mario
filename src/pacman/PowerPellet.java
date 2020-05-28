package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class PowerPellet extends Dot {

	public PowerPellet(int x, int y) {
		super(x, y);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fill(new Ellipse2D.Double(getX() + 8, getY() + 8, 15, 15));
	}
	
}
