package pacman;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Board extends JComponent {

	private ArrayList<Tile> allTiles = new ArrayList<Tile>();
	
	//Add tiles here, there might be an easier way but it might be manual
	public Board() {
		for (int i = 0; i < 896; i++) {
			if (i % 32 == 0) {
				allTiles.add(new Tile(i, 0));
				allTiles.add(new Tile(i, 640));
			}
		}
		for (int i = 32; i < 640; i++) {
			if (i % 32 == 0) {
				allTiles.add(new Tile(0, i));
				allTiles.add(new Tile(864, i));
			}
		}
	}
	
	public boolean isTile(int x, int y) {
		for (int i = 0; i < allTiles.size(); i++) {
			if (allTiles.get(i).getX() == x && allTiles.get(i).getY() == y) {
				return true;
			}
			else if (allTiles.get(i).getX() - (allTiles.get(i).getX() % 32) == x && allTiles.get(i).getY() - (allTiles.get(i).getY() % 32) == y) {
				return true;
			}
		}
		return false;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < allTiles.size(); i++) {
			allTiles.get(i).paintComponent(g);
		}
	}
	
}
