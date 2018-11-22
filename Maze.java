import java.awt.*;
import javax.swing.*;

public class Maze extends JFrame {
    public Maze(){
        // send title string to JFrame constructor
        super("My Maze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        Container surface = this.getContentPane();
        MazePanel mazePanel = new MazePanel();
        surface.add(mazePanel, BorderLayout.CENTER);

    } // end constructor
    public static void main(String[] args){
        new Maze();
    } // end main

} // end Maze