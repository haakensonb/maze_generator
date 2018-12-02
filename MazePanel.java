import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TimerTask;
import java.util.ArrayList;

public class MazePanel extends JPanel {
    GridGraph graph;

    public MazePanel(GridGraph graph){
        this.setBackground(Color.black);
        this.graph = graph;

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(30));
        int vertexWidth = 30;
        int vertexHeight = 30;

        if(Maze.showingAnimation && (this.graph.verticesToAnimate.isEmpty() == false)){
            for(int i=0; i < Maze.count; i++){
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
        } else if(graph.vertices.isEmpty() == false){
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