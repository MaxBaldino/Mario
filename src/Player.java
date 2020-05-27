import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Player extends JComponent {

	private Direction nextDirection;
	private int dx;
	private int dy;
	
	public Player(int x, int y) {
		setSize(32, 32);
		setLocation(x, y);
		nextDirection = Direction.DOWN;
		dx = 0;
		dy = 0;
	}
	
	public void update() {
		if (nextDirection == Direction.DOWN && board.isTile(getX() - (getX() % 32), getY() + 32 - ((getY() + 32) % 32))) {
			setLocation(getX(), getY() + dy);
		}
		else if (nextDirection == Direction.UP && board.isTile(getX() - (getX() % 32), getY() - 32 - ((getY() + 32) % 32))) {
			setLocation(getX(), getY() - dy);
		}
		else if (nextDirection == Direction.RIGHT && board.isTile(getX() + 32 - (getX() % 32), - ((getY() + 32) % 32))) {
			setLocation(getX() + dx, getY());
		}
		else if (nextDirection == Direction.LEFT && board.isTile(getX() - 32 - (getX() % 32), - ((getY() + 32) % 32))) {
			setLocation(getX() - dx, getY());
		}
		else if (dy > 0 && board.isTile(getX() - (getX() % 32), getY() + 32 - ((getY() + 32) % 32)) {
			setLocation(getX(), getY() + dy);
		}
		else if (dy < 0 && board.isTile(getX() - (getX() % 32), getY() - 32 - ((getY() + 32) % 32)) {
			setLocation(getX(), getY() - dy);
		}
		else if (dx > 0 && board.isTile(getX() + 32 - (getX() % 32), getY() - ((getY() + 32) % 32)) {
			setLocation(getX() + dx, getY());
		}
		else if (dx < 0 && board.isTile(getX() - 32 - (getX() % 32), getY() - ((getY() + 32) % 32)) {
			setLocation(getX() - dx, getY());
		}
	}
	
	public int getdx() {
		return dx;
	}
	
	public int getdy() {
		return dy;
	}
	
	public void setdx(int newdx) {
		dx = newdx;
	}
	
	public void setdy(int newdy) {
		dy = newdy;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.YELLOW);
		g2.fill(new Ellipse2D.Double(0, 0, 15, 15));
		g2.setColor(Color.ORANGE);
		g2.draw(new Ellipse2D.Double(0, 0, 15, 15));
	}
	
}
