import java.util.Map;
import java.util.HashMap;

public class Graph {
    private HashMap<String, Vertex> vertices;

    public Graph(int numOfRows, int numOfCols){
        this.buildGridGraph(numOfRows, numOfCols);

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
                vertex.addEdge(vertexId, ("V"+numOfCols));

            // middle of top row
            } else if(i > 0 && i < (numOfCols-1)){
                // vertex before
                vertex.addEdge(vertexId, ("V"+(i-1)));
                //vertex after
                vertex.addEdge(vertexId, ("V"+(i+1)));
                //vertex below
                vertex.addEdge(vertexId, ("V"+(i+numOfCols)));

            // top right corner
            } else if(i == numOfCols-1){
                // vertex before
                vertex.addEdge(vertexId, ("V"+(i-1)));
                // vertex under
                vertex.addEdge(vertexId, ("V"+(i+numOfCols)));
            }

            this.vertices.put(vertexId, vertex);
        }
    } // end buildGridGraph

    public static void main(String[] args){
        Graph g = new Graph(3, 3);

        // iterate through the vertices using for each loop
        for(Map.Entry<String, Vertex> vertex : g.vertices.entrySet()){
            System.out.println("Vertex: " + vertex.getKey());
            vertex.getValue().getEdgesOverview();
            System.out.println();
        }

    } // end main

} // end Graph