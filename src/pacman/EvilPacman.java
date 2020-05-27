package pacman;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EvilPacman extends Pacman implements Runnable{

    private Scene scene;
    Random random = new Random();
    private Pacman pacman;
    public int direction = 1; // Assuming, my evilpacman is facing right. Right is 1 and left would be -1
    Board board;

    public EvilPacman(int xAxis, int yAxis, String imagePath, boolean isAlive, Scene scene, Pacman pacman, Board board) {
        super(xAxis, yAxis, imagePath, isAlive, board);
        this.board = board;
        this.scene = scene;
        this.pacman = pacman;
    }

    /*
      I will make the game end???
      Let's say restart the scene.
     */
    public void kill()
    {
        Rectangle pacmanRect = new Rectangle(pacman.getX(),pacman.getY(),25,25);
        Rectangle evilRect = new Rectangle(this.getX(),this.getY(),20,20);
        //System.out.println("Evil's X : "+getX());
        //System.out.println("Evil's Y: "+getY());
       // System.out.println("GOOD's X : "+pacman.getX());
       // System.out.println("GOOD's Y: "+pacman.getY());
        if(pacmanRect.contains(evilRect))
        {
            JOptionPane.showMessageDialog(null,"You died");
            System.out.println("I died");
            System.exit(0);
        }

    }


    @Override
    public void run() {
        while (true)
        {
            try{
                Thread.sleep(random.nextInt(100));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Thread interrupted");
                System.exit(1);
            }

            if(getX() <= new Random().nextInt(50))
            {
                direction = 1;
                setImagePath("images//evil_pacman_right.png");
            }
            else if(getX() >= 450)
            {
                direction = - 1;
                setImagePath("images//evil_pacman_left.png");
            }

            kill();
            setX(getX() + 3 * direction);
            scene.repaint();


        }
    }
}
