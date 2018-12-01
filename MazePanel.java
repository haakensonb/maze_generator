import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TimerTask;
import java.util.ArrayList;

public class MazePanel extends JPanel {
    GridGraph graph;
    static int count = 0;
    static boolean showingAnimation = false;
    // has to be static because of timer
    static int numOfVerticesToAnimate;

    public MazePanel(GridGraph graph){
        this.setBackground(Color.black);
        this.graph = graph;
        numOfVerticesToAnimate = this.graph.verticesToAnimate.size();
    }

    public void animate(){

        if(!showingAnimation){
            toggleShowingAnimation();
            // make sure count is reset each time
            count = 0;
            Timer timer = new Timer(100, new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    MazePanel.count++;
                    if(MazePanel.count == numOfVerticesToAnimate){
                        // stop timer
                        ((Timer)e.getSource()).stop();
                        // stop animation
                        MazePanel.toggleShowingAnimation();
                    }
                    repaint();
                }
            });
            timer.start();

        } // end if
    }

    public static void toggleShowingAnimation(){
        if(showingAnimation){
            showingAnimation = false;
        } else if(!showingAnimation){
            showingAnimation = true;
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(30));
        int vertexWidth = 30;
        int vertexHeight = 30;

        if(showingAnimation){
            for(int i=0; i < count; i++){
                Vertex currentVertex = this.graph.verticesToAnimate.get(i);
                g.fillRect(currentVertex.getX(), currentVertex.getY(), vertexWidth, vertexHeight);

                ArrayList<String> endVertices = currentVertex.getAllAdjacentOpenPaths();
                for(int j=0; j < endVertices.size(); j++){
                    String endVertexId = endVertices.get(j);
                    Vertex endVertex = this.graph.vertices.get(endVertexId);
                    int currentVertexMidX = currentVertex.getX() + (vertexWidth / 2);
                    int currentVertexMidY = currentVertex.getY() + (vertexHeight / 2);
                    int endVertexMidX = endVertex.getX() + (vertexWidth / 2);
                    int endVertexMidY = endVertex.getY() + (vertexHeight / 2);
                    g2.drawLine(currentVertexMidX, currentVertexMidY, endVertexMidX, endVertexMidY);
                } // end inner for loop

            } // end for loop
        // otherwise it isn't showing the maze being generated and just needs to render it on the screen
        } else {
            for(Map.Entry<String, Vertex> vertex : graph.vertices.entrySet()){
                Vertex currentVertex = vertex.getValue();
                g.fillRect(currentVertex.getX(), currentVertex.getY(), vertexWidth, vertexHeight);
                // very ineffient because for now each line will be drawn twice
                ArrayList<String> endVertices = currentVertex.getAllAdjacentOpenPaths();
                for(int i=0; i < endVertices.size(); i++){
                    String endVertexId = endVertices.get(i);
                    Vertex endVertex = graph.vertices.get(endVertexId);
                    int currentVertexMidX = currentVertex.getX() + (vertexWidth / 2);
                    int currentVertexMidY = currentVertex.getY() + (vertexHeight / 2);
                    int endVertexMidX = endVertex.getX() + (vertexWidth / 2);
                    int endVertexMidY = endVertex.getY() + (vertexHeight / 2);
                    g2.drawLine(currentVertexMidX, currentVertexMidY, endVertexMidX, endVertexMidY);
                }
            }
        } // end if/else


    } // end paintComponent

} // end MazePanel