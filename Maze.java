import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Maze extends JFrame implements KeyListener, ActionListener{
    GridGraph graph;
    Player player;
    Vertex winVertex;
    MazePanel mazePanel;
    // guess this needs to be static so that playerPanel visibility can be changed within timer action listener
    static PlayerPanel playerPanel;

    static int count = 0;
    static boolean showingAnimation = false;

    public Maze(){
        // send title string to JFrame constructor
        super("My Maze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        addKeyListener(this);

        this.graph = new GridGraph(10, 10);
        this.graph.createMazeWithDFS("V0");

        Container surface = this.getContentPane();
        JLayeredPane lp = new JLayeredPane();
        this.mazePanel = new MazePanel(this.graph);
        // need to set bounds so that panel will show up
        this.mazePanel.setBounds(0, 0, 600, 600);
        lp.add(this.mazePanel, 1);
        this.player = new Player(0, 0, 30, 30, "V0");
        this.winVertex = this.graph.vertices.get("V99");
        playerPanel = new PlayerPanel(this.player, this.winVertex);
        playerPanel.setBounds(0, 0, 600, 600);
        // set player background to be transparent
        playerPanel.setOpaque(false);
        // use 0 so that player is on top
        lp.add(playerPanel, 0);
        surface.add(lp, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0));
        JButton generateBtn = new JButton("Generate");
        // kind of hacky workaround to make sure focus stays on the maze for the keylistener
        // key listening needs to be reworked or changed to keyBinding?
        generateBtn.setFocusable(false);
        generateBtn.addActionListener(this);
        bottomPanel.add(generateBtn);
        surface.add(bottomPanel, BorderLayout.SOUTH);

        this.mazePanel.setVisible(false);
        playerPanel.setVisible(false);


    } // end constructor
    public static void main(String[] args){
        new Maze();
    } // end main

    
    public void animate(){
        int numOfVerticesToAnimate = this.graph.verticesToAnimate.size();
        if(!Maze.showingAnimation){
            Maze.toggleShowingAnimation();
            // make sure count is reset each time
            Maze.count = 0;
            Timer timer = new Timer(100, new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Maze.count++;
                    if(Maze.count == numOfVerticesToAnimate){
                        // stop timer
                        ((Timer)e.getSource()).stop();
                        // stop animation
                        Maze.toggleShowingAnimation();
                        // now that the maze is done being drawn show the playerPanel
                        Maze.playerPanel.setVisible(true);
                    }
                    repaint();
                }
            });
            timer.start();

        } // end if
    } // end animate


    public static void toggleShowingAnimation(){
        if(showingAnimation){
            showingAnimation = false;
        } else if(!showingAnimation){
            showingAnimation = true;
        }
    }


    public void actionPerformed(ActionEvent e){
        System.out.println(e.getActionCommand());
        if(e.getActionCommand() == "Generate"){
            this.mazePanel.setVisible(true);
            this.animate();
        }
    } // end actionPerformed

    public void keyPressed(KeyEvent e){
        this.handlePlayerMovement(e);
        if(this.winCheck()){
            System.out.println("You Win");
            JOptionPane.showMessageDialog(this, "You found a way out of the maze! You win!", "Congratulations", JOptionPane.PLAIN_MESSAGE);
        }
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

    public boolean winCheck(){
        if((this.player.getX() == this.winVertex.getX()) && (this.player.getY() == this.winVertex.getY())){
            return true;
        } else {
            return false;
        }
    } // end winCheck

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