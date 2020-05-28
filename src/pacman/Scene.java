package pacman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scene extends JPanel{

    private int XAXIS_OF_EVIL_PACMAN = 448;
    private int YAXIS_OF_EVIL_PACMAN = 336;
    public int widhtOfScene = 910;
    public int heightOfScene = 710; 
    
    Board board = new Board();
    JFrame window = new JFrame("Pacman Game");
    Pacman pacman = new Pacman(32,32,"images//pacman_right.png", true, board);
    KeyEvents keyEvents = new KeyEvents(pacman, this);
    EvilPacman evilPacman1 = new EvilPacman(XAXIS_OF_EVIL_PACMAN, YAXIS_OF_EVIL_PACMAN,
            "images//evil_pacman_right.png",true, this, pacman, board);
    EvilPacman evilPacman2 = new EvilPacman(XAXIS_OF_EVIL_PACMAN, YAXIS_OF_EVIL_PACMAN,
            "images//evil_pacman_right.png",true, this, pacman, board);
    EvilPacman evilPacman3 = new EvilPacman(XAXIS_OF_EVIL_PACMAN, YAXIS_OF_EVIL_PACMAN,
            "images//evil_pacman_right.png",true, this, pacman, board);
    EvilPacman evilPacman4 = new EvilPacman(XAXIS_OF_EVIL_PACMAN, YAXIS_OF_EVIL_PACMAN,
            "images//evil_pacman_right.png",true, this, pacman, board);



    public Scene()
    {
        window.addKeyListener(keyEvents);
        window.setFocusable(true);
        window.add(this);
        //Thread thread = new Thread(evilPacman1);
        //thread.start(); // It basically calls the run method
       // Thread thread2 = new Thread(evilPacman2);
        //thread2.start();
        //Thread thread3 = new Thread(evilPacman3);
        //thread3.start();
        //Thread thread4 = new Thread(evilPacman4);
        //thread4.start();
        window.setBackground(Color.BLACK);
        window.setResizable(false);

        window.setSize(widhtOfScene,heightOfScene);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        evilPacman1.setdy(-1);
        
        Timer timer = new Timer(10, new ActionListener()
        		{

					@Override
					public void actionPerformed(ActionEvent e) {
						pacman.update();
						if (board.checkCollision(pacman, evilPacman1) && board.checkCollision(pacman, evilPacman2) && board.checkCollision(pacman, evilPacman3) && board.checkCollision(pacman, evilPacman4)) {
							evilPacman1.kill();
						}
						evilPacman1.update();
						evilPacman2.update();
						evilPacman3.update();
						evilPacman4.update();
						
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
