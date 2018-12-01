import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        // lp.setPreferredSize(new Dimension(600, 600));
        MazePanel mazePanel = new MazePanel(this.graph);
        // need to set bounds so that panel will show up
        mazePanel.setBounds(0, 0, 600, 600);
        lp.add(mazePanel, 1);
        this.player = new Player(0, 0, 30, 30, "V0");
        PlayerPanel playerPanel = new PlayerPanel(this.player);
        playerPanel.setBounds(0, 0, 600, 600);
        // set player background to be transparent
        playerPanel.setOpaque(false);
        // use 0 so that player is on top
        lp.add(playerPanel, 0);
        surface.add(lp, BorderLayout.CENTER);

    } // end constructor
    public static void main(String[] args){
        new Maze();
    } // end main

    public void keyPressed(KeyEvent e){
        this.handlePlayerMovement(e);
    } // end keyPressed

    public void handlePlayerMovement(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP:
                this.tryMove("UP");
                break;
            case KeyEvent.VK_RIGHT:
                this.tryMove("RIGHT");
                break;
            case KeyEvent.VK_DOWN:
                this.tryMove("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                this.tryMove("LEFT");
                break;
        } // end switch statement

    } // end handlePlayerMovement

    public void tryMove(String direction){
        Vertex playerVertex = this.graph.vertices.get(this.player.getCurrentVertexId());
        ArrayList<String> allOpenPaths = playerVertex.getAllAdjacentOpenPaths();
        int playerX = this.player.getX();
        int playerY = this.player.getY();
        for(int i=0; i < allOpenPaths.size(); i++){
            String currentEndVertexId = allOpenPaths.get(i);
            Vertex currentEndVertex = this.graph.vertices.get(currentEndVertexId);
            int possibleX = currentEndVertex.getX();
            int possibleY = currentEndVertex.getY();
            switch(direction){
                case "UP":
                    // counter intuitive because swing's X,Y start from the top left corner
                    // meaning that the higher the Y element appears on the screen, the lower it's actual int value will be
                    if(possibleY < playerY){
                        this.player.movePlayer(possibleX, possibleY, currentEndVertexId);
                        this.repaint();
                    }
                    break;
                
                case "RIGHT":
                    if(possibleX > playerX){
                        this.player.movePlayer(possibleX, possibleY, currentEndVertexId);
                        this.repaint();
                    }
                    break;
                
                case "DOWN":
                    if(possibleY > playerY){
                        this.player.movePlayer(possibleX, possibleY, currentEndVertexId);
                        this.repaint();
                    }
                    break;
                
                case "LEFT":
                    if(possibleX < playerX){
                        this.player.movePlayer(possibleX, possibleY, currentEndVertexId);
                        this.repaint();
                    }
                    break;

                default:
                    break;
            } // end switch statement

        } // end for loop

    } // end tryMove

    public boolean isPlayerPathOpen(String currentVertexId, String destinationVertexId){
        Vertex currentVertex = this.graph.vertices.get(currentVertexId);
        ArrayList<String> adjacentOpenPaths = currentVertex.getAllAdjacentOpenPaths();
        if(adjacentOpenPaths.contains(destinationVertexId)){
            return true;
        }
        return false;
    } // end isPlayerPathOpen

    // need to be here because KeyListener is implemented but we may not actually need to use these
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

} // end Maze