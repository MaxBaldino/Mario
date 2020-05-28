package pacman;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEvents implements KeyListener {

    private Pacman pacman;
    private Scene scene;
    private String right = "images//pacman_right.png";
    private String left = "images//pacman_left.png";
    private String up_left = "images//pacman_left_up.png";
    private String up_right = "images//pacman_right_up.png";
    private String down_left = "images//pacman_left_down.png";
    private String down_right = "images//pacman_right_down.png";
    private String lastMove = "";

    private int speed = 5;

    public KeyEvents(Pacman pacman, Scene scene)
    {
        this.scene = scene;
        this.pacman = pacman;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT)
        {
            pacman.setNextDir(Direction.LEFT);
            scene.repaint();

            lastMove = left;

        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
        {
        	pacman.setNextDir(Direction.RIGHT);
           
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_UP)
        {
        	pacman.setNextDir(Direction.UP);
            
            scene.repaint();

        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN)
        {
        	pacman.setNextDir(Direction.DOWN);
            
            scene.repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
