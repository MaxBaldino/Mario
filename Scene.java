package pacman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Scene extends JPanel{

    public int widhtOfScene = 910;  
    public int heightOfScene = 710; 
    private int power;
    

    Board board = new Board(this);
    
    JFrame window = new JFrame("Pacman Game");
    Pacman pacman = new Pacman(432,416,"images//pacman_right.png", true, board);
    KeyEvents keyEvents = new KeyEvents(pacman, this);
    EvilPacman evilPacman1 = new EvilPacman(32, 32,
            "images//ghostblue1.png",true, this, pacman, board);
    EvilPacman evilPacman2 = new EvilPacman(832, 32,
            "images//ghostpink1.png",true, this, pacman, board);
    EvilPacman evilPacman3 = new EvilPacman(832, 608,
            "images//ghostred1.png",true, this, pacman, board);
    EvilPacman evilPacman4 = new EvilPacman(32, 608,
            "images//ghostyellow1.png",true, this, pacman, board);



    public Scene()
    {
        window.addKeyListener(keyEvents);
        window.setFocusable(true);
        window.add(this);
        //Thread thread = new Thread(evilPacman1);
        //thread.start(); // It basically calls the run method
        window.setSize(widhtOfScene,heightOfScene);
        window.setVisible(true);
        window.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);
        window.getContentPane().setBackground(Color.BLACK);
        window.setResizable(false);
        window.add(pacman);
        window.add(evilPacman1);
        window.add(evilPacman2);
        window.add(evilPacman3);
        window.add(evilPacman4);
        window.add(board);
        
        power = 0;
       
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        evilPacman1.setdy(-1);
        
        Timer timer = new Timer(10, new ActionListener()
        		{

					@Override
					public void actionPerformed(ActionEvent e) {
						pacman.update();
						
						evilPacman1.update();
						evilPacman2.update();
						evilPacman3.update();
						evilPacman4.update();
						
						if (checkCollision(pacman, evilPacman1)) {
							evilPacman1.kill();
						}
						if (checkCollision(pacman, evilPacman2)) {
							evilPacman2.kill();
						}
						if (checkCollision(pacman, evilPacman3)) {
							evilPacman3.kill();
						}
						if (checkCollision(pacman, evilPacman4)) {
							evilPacman4.kill();
						}
						
						repaint();
						if (power > 0) {
							power--;
						}
						
					}
        			
        		});
        timer.start();

    }
    
    public boolean checkCollision(JComponent other, JComponent other2) {
		return ((other.getX() < other2.getX() + other2.getWidth()) && (other.getY() < other2.getY() + other2.getHeight()) && (other.getY() + other.getHeight() > other2.getY()) && (other.getX() + other.getWidth() > other2.getX()));
	}
    
    public int getPower() {
    	return power;
    }
    public void setPower(int pow) {
    	power = pow;
    }

    public void paint(Graphics graphics)
    {
    	
    	Graphics2D g2 = (Graphics2D) graphics;
    	g2.setColor(Color.BLACK);
    	g2.draw(new Rectangle2D.Double(0, 0, 896, 672));
    	board.paintComponent(graphics);
        pacman.drawPacman(g2);
        evilPacman1.drawPacman(g2);
        evilPacman2.drawPacman(g2);
        evilPacman3.drawPacman(g2);
        evilPacman4.drawPacman(g2);

    }
}