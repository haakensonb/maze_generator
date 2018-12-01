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

    public MazePanel(GridGraph graph){
        this.setBackground(Color.black);
        this.graph = graph;
        // this.graph = new GridGraph(10, 10);
        // this.graph.createMazeWithDFS("V0");

        Timer timer = new Timer(100, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                MazePanel.count++;
                if(MazePanel.count == 100){
                    ((Timer)e.getSource()).stop();
                }
                repaint();
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(30));
        // g.fillRect(0, 0, 30, 30);
        int vertexWidth = 30;
        int vertexHeight = 30;

        for(int i=0; i < count; i++){
            Vertex currentVertex = this.graph.verticesToAnimate.get(i);
            g.fillRect(currentVertex.getX(), currentVertex.getY(), vertexWidth, vertexHeight);
        }

        // for(Map.Entry<String, Vertex> vertex : graph.vertices.entrySet()){
        //     Vertex currentVertex = vertex.getValue();
        //     g.fillRect(currentVertex.getX(), currentVertex.getY(), vertexWidth, vertexHeight);
        //     // very ineffient because for now each line will be drawn twice
        //     ArrayList<String> endVertices = currentVertex.getAllAdjacentOpenPaths();
        //     for(int i=0; i < endVertices.size(); i++){
        //         String endVertexId = endVertices.get(i);
        //         Vertex endVertex = graph.vertices.get(endVertexId);
        //         // super messy and should maybe use doubles/floats rather than ints
        //         int currentVertexMidX = currentVertex.getX() + (vertexWidth / 2);
        //         int currentVertexMidY = currentVertex.getY() + (vertexHeight / 2);
        //         int endVertexMidX = endVertex.getX() + (vertexWidth / 2);
        //         int endVertexMidY = endVertex.getY() + (vertexHeight / 2);
        //         g2.drawLine(currentVertexMidX, currentVertexMidY, endVertexMidX, endVertexMidY);
        //     }
        // }
    }
} // end MazePanel