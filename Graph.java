import java.util.HashMap;

public class Graph {
    private HashMap<String, Vertex> vertices;

    public Graph(){

    } // end constructor

    public void buildGridGraph(int numOfRows, int numOfCols){
        int totalVertices = numOfRows * numOfCols;
        this.vertices = new HashMap<>();
        for(int i=0; i < totalVertices; i++){
            String vertexId = "V" + i;
            Vertex vertex = new Vertex();

            // top left corner
            if(i == 0){
                // vertex to right of top left corner
                vertex.addEdge(vertexId, "V1");
                // vertex under top left corner
                vertex.addEdge(vertexId, ("V"+i+numOfCols));
            }

            this.vertices.put(vertexId, vertex);
        }
    } // end buildGridGraph

    public static void main(){} // end main

} // end Graph