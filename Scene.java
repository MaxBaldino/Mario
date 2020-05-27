import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel{

    private int XAXIS_OF_EVIL_PACMAN = 10;
    private int YAXIS_OF_EVIL_PACMAN = 10;
    public int widhtOfScene = 510;
    public int heightOfScene = 333;

    JFrame window = new JFrame("Pacman Game");
    Pacman pacman = new Pacman(1,1,"images//pacman_right.png", true);
    KeyEvents keyEvents = new KeyEvents(pacman, this);
    EvilPacman evilPacman1 = new EvilPacman(XAXIS_OF_EVIL_PACMAN + 20, YAXIS_OF_EVIL_PACMAN + 100,
            "images//evil_pacman_right.png",true, this, pacman);
    EvilPacman evilPacman2 = new EvilPacman(XAXIS_OF_EVIL_PACMAN + 100, YAXIS_OF_EVIL_PACMAN + 200,
            "images//evil_pacman_right.png",true, this, pacman);



    public Scene()
    {
        window.addKeyListener(keyEvents);
        window.setFocusable(true);
        window.add(this);
        Thread thread = new Thread(evilPacman1);
        thread.start(); // It basically calls the run method
        Thread thread2 = new Thread(evilPacman2);
        thread2.start();

        window.setSize(widhtOfScene,heightOfScene);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }


    public void paint(Graphics graphics)
    {
        ImageIcon imageIcon = new ImageIcon("images//background.jpg");
        graphics.drawImage(imageIcon.getImage(),0,0,null);
        pacman.drawPacman(graphics);
        evilPacman1.drawPacman(graphics);
        evilPacman2.drawPacman(graphics);
    }
}
