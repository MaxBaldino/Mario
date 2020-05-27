import javax.swing.*;
import java.awt.*;

public class Pacman {

    private int xAxis;
    private int yAxis;
    private String imagePath;
    private boolean isAlive;

    public Pacman(int xAxis, int yAxis, String imagePath, boolean isAlive)
    {
        setXAxis(xAxis);
        setYAxis(yAxis);
        setImagePath(imagePath);
        setIsAlive(isAlive);
    }

    public void setXAxis(int xAxis)
    {
        // My player to remain on the screen while he is alive
        if(isAlive)
        {
            // I will be able to move in a certain bound
            // I want a window of 500 pixels
            if(this.xAxis >= 470)
            {
                this.xAxis = 470;
            }
            else if(this.xAxis <= 0)
            {
                this.xAxis = 0;
            }
            else
                this.xAxis = xAxis; // This only happens when my player is in the window

        }
        else
        {
            this.xAxis = xAxis;
        }
    }

    public void setYAxis(int yAxis)
    {
        // My player to remain on the screen while he is alive
        if(isAlive)
        {
            // I will be able to move in a certain bound
            // I want a window of 500 pixels
            if(this.yAxis >= 300)
            {
                this.yAxis = 300;
            }
            else if(this.yAxis <= 0)
            {
                this.yAxis = 0;
            }
            else
                this.yAxis = yAxis; // This only happens when my player is in the window

        }
        else
        {
            this.yAxis = yAxis;
        }

    }

    public void setImagePath(String imagePath)
    {
        if(imagePath == null)
        {
            JOptionPane.showMessageDialog(null, "Image is null");
            System.exit(1);
        }
        else
            this.imagePath = imagePath;
    }

    public void setIsAlive(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    public int getyAxis() {
        return yAxis;
    }

    public int getxAxis() {
        return xAxis;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isAlive() {
        return isAlive;
    }


    // This function will allow me to draw the pacman image!
    public void drawPacman(Graphics graphics)
    {
        ImageIcon img = new ImageIcon(imagePath);
        graphics.drawImage(img.getImage(),xAxis,yAxis,null);
    }
}
