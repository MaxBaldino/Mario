package pacman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scene extends JPanel{

    private int XAXIS_OF_EVIL_PACMAN = 10;
    private int YAXIS_OF_EVIL_PACMAN = 10;
    public int widhtOfScene = 910;
    public int heightOfScene = 710; 
    
    Board board = new Board();
    JFrame window = new JFrame("Pacman Game");
    Pacman pacman = new Pacman(32,32,"images//pacman_right.png", true, board);
    KeyEvents keyEvents = new KeyEvents(pacman, this);
    EvilPacman evilPacman1 = new EvilPacman(XAXIS_OF_EVIL_PACMAN + 20, YAXIS_OF_EVIL_PACMAN + 100,
            "images//evil_pacman_right.png",true, this, pacman, board);
    EvilPacman evilPacman2 = new EvilPacman(XAXIS_OF_EVIL_PACMAN + 100, YAXIS_OF_EVIL_PACMAN + 200,
            "images//evil_pacman_right.png",true, this, pacman, board);



    public Scene()
    {
        window.addKeyListener(keyEvents);
        window.setFocusable(true);
        window.add(this);
        Thread thread = new Thread(evilPacman1);
        thread.start(); // It basically calls the run method
        Thread thread2 = new Thread(evilPacman2);
        thread2.start();
        window.setBackground(Color.BLACK);
        window.setResizable(false);

        window.setSize(widhtOfScene,heightOfScene);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        Timer timer = new Timer(10, new ActionListener()
        		{

					@Override
					public void actionPerformed(ActionEvent e) {
						pacman.update();
						
						repaint();
						
					}
        			
        		});
        timer.start();

    }


    public void paint(Graphics graphics)
    {
    	
    	Graphics2D g2 = (Graphics2D) graphics;
    	board.paintComponent(graphics);
        pacman.drawPacman(graphics);
        evilPacman1.drawPacman(graphics);
        evilPacman2.drawPacman(graphics);
    }
}
