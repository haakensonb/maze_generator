import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.ArrayList;

public class MazePanel extends JPanel {
    GridGraph graph;

    public MazePanel(){
        this.setBackground(Color.black);
        this.graph = new GridGraph(10, 10);
        this.graph.createMazeWithDFS("V0");
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(30));
        // g.fillRect(0, 0, 30, 30);
        int vertexWidth = 30;
        int vertexHeight = 30;
        for(Map.Entry<String, Vertex> vertex : graph.vertices.entrySet()){
            Vertex currentVertex = vertex.getValue();
            g.fillRect(currentVertex.getX(), currentVertex.getY(), vertexWidth, vertexHeight);
            // very ineffient because for now each line will be drawn twice
            ArrayList<String> endVertices = currentVertex.getAllEndVertices();
            for(int i=0; i < endVertices.size(); i++){
                String endVertexId = endVertices.get(i);
                Vertex endVertex = graph.vertices.get(endVertexId);
                // super messy and should maybe use doubles/floats rather than ints
                int currentVertexMidX = currentVertex.getX() + (vertexWidth / 2);
                int currentVertexMidY = currentVertex.getY() + (vertexHeight / 2);
                int endVertexMidX = endVertex.getX() + (vertexWidth / 2);
                int endVertexMidY = endVertex.getY() + (vertexHeight / 2);
                g2.drawLine(currentVertexMidX, currentVertexMidY, endVertexMidX, endVertexMidY);
            }
        }
    }
} // end MazePanel