import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel {
    private Player player;
    private Vertex winVertex;

    public PlayerPanel(Player player, Vertex winVertex){
        this.player = player;
        this.winVertex = winVertex;
    } // end constructor

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        // paint winVertex
        g.setColor(Color.green);
        g.fillRect(this.winVertex.getX(), this.winVertex.getY(), this.player.getWidth(), this.player.getHeight());
        // paint player
        g.setColor(Color.red);
        g.fillRect(this.player.getX(), this.player.getY(), this.player.getWidth(), this.player.getHeight());

    } // end paintComponent

} // end PlayerPanel