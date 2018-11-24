import java.awt.*;
import javax.swing.*;

public class Player extends JPanel{
    private int x;
    private int y;
    private int width;
    private int height;
    private String currentVertexId;

    public Player(int x, int y, int width, int height, String currentVertexId){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentVertexId = currentVertexId;
    } // end constructor

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public String getCurrentVertexId(){
        return this.currentVertexId;
    }

    public void movePlayer(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, this.width, this.height);
    } // end paintComponent

} // end Player