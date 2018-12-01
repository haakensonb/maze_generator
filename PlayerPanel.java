import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel {
    Player player;

    public PlayerPanel(Player player){
        this.player = player;
    } // end constructor

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillRect(this.player.getX(), this.player.getY(), this.player.getWidth(), this.player.getHeight());
    } // end paintComponent

} // end PlayerPanel