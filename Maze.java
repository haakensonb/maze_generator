import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.ArrayList;

public class Maze extends JFrame implements KeyListener{
    GridGraph graph;
    Player player;

    public Maze(){
        // send title string to JFrame constructor
        super("My Maze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        addKeyListener(this);

        this.graph = new GridGraph(10, 10);
        this.graph.createMazeWithDFS("V0");

        Container surface = this.getContentPane();
        JLayeredPane lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(600, 600));
        MazePanel mazePanel = new MazePanel(this.graph);
        // need to set bounds so that panel will show up
        mazePanel.setBounds(0, 0, 600, 600);
        lp.add(mazePanel, 1);
        this.player = new Player(0, 0, 30, 30, "V0");
        this.player.setBounds(0, 0, 600, 600);
        // set player background to be transparent
        this.player.setOpaque(false);
        // use 0 so that player is on top
        lp.add(player, 0);
        surface.add(lp, BorderLayout.CENTER);

    } // end constructor
    public static void main(String[] args){
        new Maze();
    } // end main

    public void keyPressed(KeyEvent e){
        this.handlePlayerMovement(e);
    }

    public void handlePlayerMovement(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP:
                System.out.println("up");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("right");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("down");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("left");
                break;
        }
    }

    public boolean isPlayerPathOpen(String currentVertexId, String destinationVertexId){
        Vertex currentVertex = this.graph.vertices.get(currentVertexId);
        ArrayList<String> adjacentOpenPaths = currentVertex.getAllAdjacentOpenPaths();
        if(adjacentOpenPaths.contains(destinationVertexId)){
            return true;
        }
        return false;
    }

    // need to be here because KeyListener is implemented but we may not actually need to use these
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

} // end Maze