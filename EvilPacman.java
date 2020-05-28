package pacman;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EvilPacman extends Pacman implements Runnable{

    private Scene scene;
    Random random = new Random();
    private Pacman pacman;
    public int direction = 1; // Assuming, my evilpacman is facing right. Right is 1 and left would be -1
    private Board board;
    private int counter;
    private String color;
    private int speedMultiplier;
    private int spawnX, spawnY;
    private int respawnCounter;

    public EvilPacman(int xAxis, int yAxis, String imagePath, boolean isAlive, Scene scene, Pacman pacman, Board board) {
        super(xAxis, yAxis, imagePath, isAlive, board);
        this.board = board;
        this.scene = scene;
        this.pacman = pacman;
        counter = 0;
        if (imagePath.indexOf("pink") != -1) {
        	color = "PINK";
        }
        if (imagePath.indexOf("blue") != -1) {
        	color = "BLUE";
        }
        if (imagePath.indexOf("yellow") != -1) {
        	color = "YELLOW";
        }
        if (imagePath.indexOf("red") != -1) {
        	color = "RED";
        }
        if (color.equals("RED")) {
        	speedMultiplier = 2;
        }
        else {
        	speedMultiplier = 1;
        }
        spawnX = xAxis;
        spawnY = yAxis;
        respawnCounter = 0;
    }

    /*
      I will make the game end???
      Let's say restart the scene.
     */
    public void kill()
    {
    	if (scene.getPower() == 0) {
    		board.saveScore();
            JOptionPane.showMessageDialog(null,"You died");
            System.out.println("I died");
            System.exit(0);
    	}
    	else if (respawnCounter == 0) {
    		board.incrementScore(10);
    		setLocation(spawnX, spawnY);
    		respawnCounter = 100;
    		setdx(0);
    		setdy(0);
    	}
    }

    
    public void changeDirection(double chance) {
    	if (respawnCounter != 0) {
    		return;
    	}
    	if (Math.random() < chance) {
	    	if (scene.getPower() == 0) {
	    		if (Math.random() < 0.5) {
	    			if (pacman.getY() > getY()) {
	    				setNextDir(Direction.DOWN);
	    			}
	    			else if (pacman.getY() < getY()) {
	    				setNextDir(Direction.UP);
	    			}
	    			else if (pacman.getX() > getX()) {
	    				setNextDir(Direction.RIGHT);
	    			}
	    			else if (pacman.getX() < getX()) {
	    				setNextDir(Direction.LEFT);
	    			}
	    		}
	    		else {
	    			if (pacman.getX() > getX()) {
	    				setNextDir(Direction.RIGHT);
	    			}
	    			else if (pacman.getX() < getX()) {
	    				setNextDir(Direction.LEFT); 
	    			}
	    			else if (pacman.getY() > getY()) {
	    				setNextDir(Direction.DOWN);
	    			}
	    			else if (pacman.getY() < getY()) {
	    				setNextDir(Direction.UP);
	    			}
	    			
	    		}
	    		}
	    	else {
	    		if (Math.random() < 0.5) {
	    			if (pacman.getY() > getY()) {
	    				setNextDir(Direction.UP);
	    			}
	    			else if (pacman.getY() < getY()) {
	    				setNextDir(Direction.DOWN);
	    			}
	    			else if (pacman.getX() > getX()) {
	    				setNextDir(Direction.LEFT);
	    			}
	    			else if (pacman.getX() < getX()) {
	    				setNextDir(Direction.RIGHT);
	    			}
	    			if (pacman.getY() == getY()) {
	    				if (getY() < scene.getWidth() - scene.window.getInsets().left * 2) {
	    					setNextDir(Direction.DOWN);
	    				}
	    				else {
	    					setNextDir(Direction.UP);
	    				}
	    			}
	    			if (pacman.getX() == getX()) {
	    				if (getX() < scene.getWidth() - scene.window.getInsets().bottom - scene.window.getInsets().top) {
	    					setNextDir(Direction.RIGHT);
	    				}
	    				else {
	    					setNextDir(Direction.LEFT);
	    				}
	    			}
	    		}
	    		else {
	    			if (pacman.getX() > getX()) {
	    				setNextDir(Direction.LEFT);
	    			}
	    			else if (pacman.getX() < getX()) {
	    				setNextDir(Direction.RIGHT);
	    			}
	    			else if (pacman.getY() > getY()) {
	    				setNextDir(Direction.UP);
	    			}
	    			else if (pacman.getY() < getY()) {
	    				setNextDir(Direction.DOWN);
	    			}
	    			if (pacman.getY() == getY()) {
	    				if (getY() < scene.getWidth() - scene.window.getInsets().left * 2) {
	    					setNextDir(Direction.DOWN);
	    				}
	    				else {
	    					setNextDir(Direction.UP);
	    				}
	    			}
	    			if (pacman.getX() == getX()) {
	    				if (getX() < scene.getHeight() - scene.window.getInsets().bottom - scene.window.getInsets().top) {
	    					setNextDir(Direction.RIGHT);
	    				}
	    				else {
	    					setNextDir(Direction.LEFT);
	    				}
	    			}
	    			
	    		}
	    		}
	    	}
	    	
    	}


    public void update() {
    	if (respawnCounter > 0) {
    		respawnCounter--;
    		setdx(0);
    		setdy(0);
    		if (getY() == 608) {
    			setNextDir(Direction.DOWN);
    		}
    		else if (getY() == 32) {
    			setNextDir(Direction.UP);
    		}
    	}
    	counter++;
    	changeDirection(0.01);
    	if (board.isTile(getX(), getY(), getdx(), getdy())) {
    		setdx(0);
    		setdy(0);
    		changeDirection(1);
    	}
    	if (getNextDir() == Direction.DOWN && !board.isTile(getX(), getY(), 0, 1)) {
			setdx(0);
			setdy(1 * speedMultiplier);
			setLocation(getX(), getY() + getdy());
		}
		else if (getNextDir() == Direction.UP && !board.isTile(getX(), getY(), 0, -1)) {
			setdx(0);
			setdy(-1 * speedMultiplier);
			setLocation(getX(), getY() + getdy());
		}
		else if (getNextDir() == Direction.RIGHT && !board.isTile(getX(), getY(), 1, 0)) {
			setdx(1 * speedMultiplier);
			setdy(0);
			setLocation(getX() + getdx(), getY());
		}
		else if (getNextDir() == Direction.LEFT && !board.isTile(getX() - 1, getY(), -1, 0)) {
			setdx(-1 * speedMultiplier);
			setdy(0);
			setLocation(getX() + getdx(), getY());
		}
		else if (getdy() > 0 && !board.isTile(getX(), getY() + 1)) {
			setLocation(getX(), getY() + getdy());
		}
		else if (getdy() < 0 && !board.isTile(getX(), getY() - 1)) {
			setLocation(getX(), getY() + getdy());
		}
		else if (getdx() > 0 && !board.isTile(getX() + 1, getY())) {
			setLocation(getX() + getdx(), getY());
		}
		else if (getdx() < 0 && !board.isTile(getX() - 1, getY())) {
			setLocation(getX() + getdx(), getY());
		}
    	
    	if (counter >= 20) {
    		counter = 0;
    		if (scene.getPower() > 0) {
    			if (!getImagePath().equals("images//ghostscared1.png") && !getImagePath().equals("images//ghostscared2.png")) {
    				setImagePath("images//ghostscared1.png");
    			}
    			else if(getImagePath().equals("images//ghostscared1.png"))
            		setImagePath("images//ghostscared2.png");
        		else if(getImagePath().equals("images//ghostscared2.png"))
            		setImagePath("images//ghostscared1.png");
    		}
    		else if (!getImagePath().equals("images//ghost" + color.toLowerCase() + "1.png") && !getImagePath().equals("images//ghost" + color.toLowerCase() + "2.png")) {
    			setImagePath("images//ghost" + color.toLowerCase() + "1.png");
    		}
    		if(getImagePath().equals("images//ghostblue1.png"))
        		setImagePath("images//ghostblue2.png");
        	else if(getImagePath().equals("images//ghostblue2.png"))
        		setImagePath("images//ghostblue1.png");
        	if(getImagePath().equals("images//ghostpink1.png"))
        		setImagePath("images//ghostpink2.png");
        	else if(getImagePath().equals("images//ghostpink2.png"))
        		setImagePath("images//ghostpink1.png");
        	if(getImagePath().equals("images//ghostred1.png"))
        		setImagePath("images//ghostred2.png");
        	else if(getImagePath().equals("images//ghostred2.png"))
        		setImagePath("images//ghostred1.png");
        	if(getImagePath().equals("images//ghostyellow1.png"))
        		setImagePath("images//ghostyellow2.png");
        	else if(getImagePath().equals("images//ghostyellow2.png"))
        		setImagePath("images//ghostyellow1.png");
        	
    	}
    	if (getdx() == 0 && getdy() == 0) {
    		changeDirection(1);
    	}
    	
    		
    }
    
    public void drawPacman(Graphics graphics)
    {
    	if (respawnCounter == 0) {
    		ImageIcon img = new ImageIcon(getImagePath());
    		graphics.drawImage(img.getImage(),getX(),getY(),null);
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