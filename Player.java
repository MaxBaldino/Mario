import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Player extends JComponent {

	private Direction nextDirection = DOWN;
	
	public Player(int x, int y) {
		setSize(64, 64);
		setLocation(x, y);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.YELLOW);
		g2.fill(new Ellipse2D.Double(0, 0, 15, 15));
		g2.setColor(Color.ORANGE);
		g2.draw(new Ellipse2D.Double(0, 0, 15, 15));
	}
	
}
