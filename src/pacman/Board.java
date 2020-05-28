package pacman;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;

public class Board extends JComponent {

	private ArrayList<Tile> allTiles = new ArrayList<Tile>();
	private ArrayList<Dot> allDots = new ArrayList<Dot>();
	
	private int score;
	private int highscore;
	private Scene scene;
	
	private File file = new File("highscore.txt");
	private Scanner reader;
	private String fileText = "";
	
	public Board(Scene scene) {
		this.scene = scene;
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
		for (int i = 64; i < 608; i++) {
			if (i % 32 == 0 && i != 320 && i != 160 && i != 480) {
				allTiles.add(new Tile(64, i));
				allTiles.add(new Tile(800, i));
			}
		}
		for (int i = 64; i < 832; i++) {
			if (i % 32 == 0 && i != 320 && i != 544) {
				allTiles.add(new Tile(i, 64));
				allTiles.add(new Tile(i, 576));
			}
		}
		allTiles.add(new Tile(128, 128));
		allTiles.add(new Tile(736, 128));
		allTiles.add(new Tile(128, 512));
		allTiles.add(new Tile(736, 512));
		
		for (int i = 128; i < 480; i++) {
			if (i % 32 == 0 && i != 160) {
				allTiles.add(new Tile(128, i));
				allTiles.add(new Tile(736, i));
			}
		}
		
		for (int i = 192; i < 704; i++) {
			if (i % 32 == 0 && i != 480 && i != 384) {
				allTiles.add(new Tile(i, 512));
				allTiles.add(new Tile(i, 128));
			}
		}
		
		for (int i = 192; i < 704; i++) {
			if (i % 32 == 0 && i != 544 && i != 320) {
				allTiles.add(new Tile(i, 448));
				allTiles.add(new Tile(i, 192));
			}
		}
		
		for (int i = 192; i < 448; i++) {
			if (i % 32 == 0 && i != 416 && i != 288) {
				allTiles.add(new Tile(192, i));
				allTiles.add(new Tile(672, i));
			}
		}
		
		for (int i = 256; i < 416; i++) {
			if (i % 32 == 0) {
				allTiles.add(new Tile(256, i));
				allTiles.add(new Tile(608, i));
			}
		}
		
		for (int i = 256; i < 640; i++) {
			if (i % 32 == 0 && i != 512 && i != 352) {
				allTiles.add(new Tile(i, 448));
				allTiles.add(new Tile(i, 256));
			}
		}
		
		for (int i = 320; i < 416; i++) {
			if (i % 32 == 0) {
				allTiles.add(new Tile(320, i));
				allTiles.add(new Tile(544, i));
			}
		}
		
		for (int i = 384; i < 576; i++) {
			if (i % 32 == 0 && i != 512 && i != 352) {
				allTiles.add(new Tile(i, 384));
			}
		}
		allTiles.add(new Tile(384, 352));
		allTiles.add(new Tile(480, 352));
		allTiles.add(new Tile(384, 320));
		allTiles.add(new Tile(480, 320));
		allTiles.add(new Tile(416, 320));
		allTiles.add(new Tile(448, 320));
		
		for (int i = 0; i < 896; i += 32) {
			for (int j = 0; j < 672; j+= 32) {
				if (!isTile(i, j)) {
					allDots.add(new Dot(i, j));
				}
			}
		}
		deleteDot(416, 352);
		deleteDot(448, 352);
		
		replaceDot(96, 96);
		replaceDot(96, 544);
		replaceDot(768, 96);
		replaceDot(768, 544);
		
		score = 0;
		
		
		try {
			reader = new Scanner(file);
			while (reader.hasNext()) {
				fileText += reader.nextLine();
			}
		}
		catch (Exception e) {
			try {
				file.createNewFile();
				reader = new Scanner(file);
				while (reader.hasNext()) {
					fileText += reader.nextLine();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (fileText.equals("")) {
			highscore = 0;
		}
		else {
			highscore = Integer.parseInt(fileText);
		}
		
		reader.close();

	}
	
	public void createNewLevel() {
		for (int i = 0; i < 896; i += 32) {
			for (int j = 0; j < 672; j+= 32) {
				if (!isTile(i, j)) {
					allDots.add(new Dot(i, j));
				}
			}
		}
		deleteDot(416, 352);
		deleteDot(448, 352);
		
		replaceDot(96, 96);
		replaceDot(96, 544);
		replaceDot(768, 96);
		replaceDot(768, 544);
		
		Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.ORANGE};
		Color color = colors[(int) (Math.random() * colors.length)];
		
		for (int i = 0; i < allTiles.size(); i++) {
			allTiles.get(i).changeColor(color);
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
	
	public boolean isTile(int x, int y, int changex, int changey) {
		for (int i = 0; i < allTiles.size(); i++) {
			if (allTiles.get(i).checkCollision(x, y, changex, changey)) {
				return true;
			}
		}
		return false;
	}
	
	public void collectDot(int x, int y) {
		for (int i = 0; i < allDots.size(); i++) {
			if (allDots.get(i).getX() == x && allDots.get(i).getY() == y) {
				incrementScore();
				allDots.remove(i);
			}
		}
	}
	public void deleteDot(int x, int y) {
		for (int i = 0; i < allDots.size(); i++) {
			if (allDots.get(i).getX() == x && allDots.get(i).getY() == y) {
				allDots.remove(i);
			}
			
		}
	}
	
	public void checkForDots(JComponent other) {
		for (int i = 0; i < allDots.size(); i++) {
			if (checkCollision(other, allDots.get(i))) {
				if (allDots.get(i) instanceof PowerPellet) {
					scene.setPower(1000);
				}
				collectDot(allDots.get(i).getX(), allDots.get(i).getY());
			}
		}
		if (allDots.size() == 0) {
			createNewLevel();
		}
		if (score > highscore) {
			highscore = score;
		}
	}
	
	public void saveScore() {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write("" + highscore);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void replaceDot(int x, int y) {
		deleteDot(x, y);
		allDots.add(new PowerPellet(x, y));
	}
	
	public boolean checkCollision(JComponent other, JComponent other2) {
		return ((other.getX() < other2.getX() + other2.getWidth()) && (other.getY() < other2.getY() + other2.getHeight()) && (other.getY() + other.getHeight() > other2.getY()) && (other.getX() + other.getWidth() > other2.getX()));
	}
	
	public void incrementScore() {
		score++;
	}
	
	public void incrementScore(int num) {
		score += num;
	}

	public int getScore() {
		return score;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < allTiles.size(); i++) {
			allTiles.get(i).paintComponent(g);
		}
		for (int i = 0; i < allDots.size(); i++) {
			allDots.get(i).paintComponent(g);
		}
		
		g2.drawString(" " + score, 436, 368);
		g2.drawString("Highscore: " + highscore, 436, 384);
	}
	
}
