import java.awt.*;
import javax.swing.*;

public class Maze extends JFrame{
    public Maze(){
        // send title string to JFrame constructor
        super("My Maze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        Container surface = this.getContentPane();
        JLayeredPane lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(600, 600));
        MazePanel mazePanel = new MazePanel();
        // need to set bounds so that panel will show up
        mazePanel.setBounds(0, 0, 600, 600);
        lp.add(mazePanel, 1);
        Player player = new Player(0, 0, 30, 30);
        player.setBounds(0, 0, 600, 600);
        // set player background to be transparent
        player.setOpaque(false);
        // use 0 so that player is on top
        lp.add(player, 0);
        surface.add(lp, BorderLayout.CENTER);

    } // end constructor
    public static void main(String[] args){
        new Maze();
    } // end main

} // end Maze