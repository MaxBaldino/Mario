import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EvilPacman extends Pacman implements Runnable{

    private Scene scene;
    Random random = new Random();
    private Pacman pacman;
    public int direction = 1; // Assuming, my evilpacman is facing right. Right is 1 and left would be -1

    public EvilPacman(int xAxis, int yAxis, String imagePath, boolean isAlive, Scene scene, Pacman pacman) {
        super(xAxis, yAxis, imagePath, isAlive);
        this.scene = scene;
        this.pacman = pacman;
    }

    /*
      I will make the game end???
      Let's say restart the scene.
     */
    public void kill()
    {
        Rectangle pacmanRect = new Rectangle(pacman.getxAxis(),pacman.getyAxis(),25,25);
        Rectangle evilRect = new Rectangle(this.getxAxis(),this.getyAxis(),20,20);
        System.out.println("Evil's X : "+getxAxis());
        System.out.println("Evil's Y: "+getyAxis());
        System.out.println("GOOD's X : "+pacman.getxAxis());
        System.out.println("GOOD's Y: "+pacman.getyAxis());
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

            if(getxAxis() <= new Random().nextInt(50))
            {
                direction = 1;
                setImagePath("images//evil_pacman_right.png");
            }
            else if(getxAxis() >= 450)
            {
                direction = - 1;
                setImagePath("images//evil_pacman_left.png");
            }

            kill();
            setXAxis(getxAxis() + 3 * direction);
            scene.repaint();


        }
    }
}
