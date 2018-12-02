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
    Container surface;
    JLayeredPane lp;
    // counter for animation
    static int count = 0;
    static boolean showingAnimation = false;
    boolean winnerFound = false;

    public Maze(){
        // send title string to JFrame constructor
        super("My Maze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        // key listener for arrow key movement
        addKeyListener(this);
        // create the center panel that holds MazePanel and PlayerPanel
        this.initCenterPanel();
        // create bottom button
        this.initBottomPanel();
        // make sure maze is visible before animation
        this.mazePanel.setVisible(true);
        // show the graph being made as an animation
        this.animate();

    } // end constructor
    public static void main(String[] args){
        new Maze();
    } // end main

    public void initBottomPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0));
        JButton generateBtn = new JButton("Generate");
        // kind of hacky workaround to make sure focus stays on the maze for the keylistener
        // key listening needs to be reworked or changed to keyBinding?
        generateBtn.setFocusable(false);
        generateBtn.addActionListener(this);
        bottomPanel.add(generateBtn);
        this.surface.add(bottomPanel, BorderLayout.SOUTH);
    } // end initBottomPanel

    public void initCenterPanel(){
        // create a grid shaped graph
        this.graph = new GridGraph(10, 10);
        // actually turn the graph into a maze
        this.graph.createMazeWithDFS("V0");

        this.surface = this.getContentPane();
        this.lp = new JLayeredPane();
        this.mazePanel = new MazePanel(this.graph);
        // need to set bounds so that panel will show up
        this.mazePanel.setBounds(0, 0, 600, 600);
        this.lp.add(this.mazePanel, 1);
        // create player at the start of maze
        this.player = new Player(0, 0, 30, 30, "V0");
        // create win area at end of maze
        this.winVertex = this.graph.vertices.get("V99");
        playerPanel = new PlayerPanel(this.player, this.winVertex);
        playerPanel.setBounds(0, 0, 600, 600);
        // set player background to be transparent
        playerPanel.setOpaque(false);
        // use 0 so that player is on top
        this.lp.add(playerPanel, 0);
        this.surface.add(lp, BorderLayout.CENTER);
        // make sure that panels are invisible for now until proper animation timing
        this.mazePanel.setVisible(false);
        playerPanel.setVisible(false);
    }
    
    public void animate(){
        int numOfVerticesToAnimate = this.graph.verticesToAnimate.size();
        // if it's not already trying to step through an animation
        if(!Maze.showingAnimation){
            // turn on animation
            Maze.toggleShowingAnimation();
            // make sure count is reset each time
            Maze.count = 0;
            // timer increments count which representings the number of vertices to draw
            Timer timer = new Timer(100, new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Maze.count++;
                    // keeps drawing more vertices each time until it reaches the end of the maze
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
    } // end toggleShowingAnimation

    public void actionPerformed(ActionEvent e){
        // if the generate button is pressed
        if(e.getActionCommand() == "Generate"){
            // as long as it isn't already in the process of animating
            if(showingAnimation == false){
                // hide player panel for reset
                playerPanel.setVisible(false);
                // reset/rebuild the graph
                this.graph.reset();
                // reset player position
                this.player.movePlayer(0, 0, "V0");
                // reset win flag
                winnerFound = false;
                // make panel is repainted
                repaint();
                // now that maze is generated we can show the panel again
                this.mazePanel.setVisible(true);
                // call animation to show building of maze
                this.animate();
            }
            
        }
    } // end actionPerformed

    public void keyPressed(KeyEvent e){
        // only let the player move if they haven't already won
        if(!winnerFound){
            this.handlePlayerMovement(e);
            if(this.winCheck()){
                JOptionPane.showMessageDialog(this, "You found a way out of the maze! You win!", "Congratulations", JOptionPane.PLAIN_MESSAGE);
            }
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
        // get the player's current vertex
        Vertex playerVertex = this.graph.vertices.get(this.player.getCurrentVertexId());
        // get all the paths available for the player to take
        ArrayList<String> allOpenPaths = playerVertex.getAllAdjacentOpenPaths();
        // get player's position
        int playerX = this.player.getX();
        int playerY = this.player.getY();
        // loop through each available path
        for(int i=0; i < allOpenPaths.size(); i++){
            // get the position of the end vertex of the current available path
            String currentEndVertexId = allOpenPaths.get(i);
            Vertex currentEndVertex = this.graph.vertices.get(currentEndVertexId);
            int possibleX = currentEndVertex.getX();
            int possibleY = currentEndVertex.getY();
            // try different direction depending on the parameter passed in
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
        // if the player's x,y position is the same as the x,y position of the winning area then they have won
        if((this.player.getX() == this.winVertex.getX()) && (this.player.getY() == this.winVertex.getY())){
            winnerFound = true;
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