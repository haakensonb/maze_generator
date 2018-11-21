import java.util.Map;
import java.util.HashMap;

public class Graph {
    private HashMap<String, Vertex> vertices;
    // stack for backtracking
    private StringStack visitedVertices = new StringStack();

    public Graph(int numOfRows, int numOfCols){
        this.buildGridGraph(numOfRows, numOfCols);

    } // end constructor

    public void buildGridGraph(int numOfRows, int numOfCols){
        int totalVertices = numOfRows * numOfCols;
        this.vertices = new HashMap<>();
        int currentRow = 0;
        for(int i=0; i < totalVertices; i++){

            // increment the current row
            if (((i % numOfCols) == 0) && (i != 0)){
                currentRow += 1; 
            }

            String vertexId = "V" + i;
            Vertex vertex = new Vertex(vertexId);

            int topLeftCornerIndex = 0;
            int topRightCornerIndex =  numOfCols-1;
            int bottomLeftCornerIndex = totalVertices-numOfCols;
            int bottomRightCornerIndex = totalVertices-1;
            int leftColumnIndex = (currentRow * numOfCols);
            int rightColumnIndex = (currentRow * numOfCols) + (numOfCols - 1);

            if(i == topLeftCornerIndex){
                // vertex to right of top left corner
                vertex.addEdge(vertexId, "V1");
                // vertex under top left corner
                vertex.addEdge(vertexId, ("V"+numOfCols));
 
            } else if (i == topRightCornerIndex){
                // vertex before
                vertex.addEdge(vertexId, ("V"+(i-1)));
                // vertex under
                vertex.addEdge(vertexId, ("V"+(i+numOfCols)));

            } else if (i == bottomLeftCornerIndex){
                // vertex above
                vertex.addEdge(vertexId, ("V"+(i-numOfCols)));
                // vertex to right
                vertex.addEdge(vertexId, ("V"+(i+1)));

            } else if (i == bottomRightCornerIndex){
                // vertex above
                vertex.addEdge(vertexId, ("V"+(i-numOfCols))); 
                // vertex to left
                vertex.addEdge(vertexId, ("V"+(i-1)));

            } else if ((i > topLeftCornerIndex) && (i < topRightCornerIndex)){
                // vertex before
                vertex.addEdge(vertexId, ("V"+(i-1)));
                //vertex after
                vertex.addEdge(vertexId, ("V"+(i+1)));
                //vertex below
                vertex.addEdge(vertexId, ("V"+(i+numOfCols))); 

            } else if (i == leftColumnIndex){
                // vertex above
                vertex.addEdge(vertexId, ("V"+(i-numOfCols)));
                // vertex to right
                vertex.addEdge(vertexId, ("V"+(i+1)));
                // vertex below
                vertex.addEdge(vertexId, ("V"+(i+numOfCols))); 

            } else if (i == rightColumnIndex){
                // vertex above
                vertex.addEdge(vertexId, ("V"+(i-numOfCols)));
                // vertex to left
                vertex.addEdge(vertexId, ("V"+(i-1)));
                // vertex below 
                vertex.addEdge(vertexId, ("V"+(i+numOfCols)));

            } else if ((i > bottomLeftCornerIndex) && (i < bottomRightCornerIndex)){
                // vertex above
                vertex.addEdge(vertexId, ("V"+(i-numOfCols)));
                // vertex to left
                vertex.addEdge(vertexId, ("V"+(i-1)));
                // vertex to right
                vertex.addEdge(vertexId, ("V"+(i+1))); 

            } else {
                // vertex above
                vertex.addEdge(vertexId, ("V"+(i-numOfCols)));
                // vertex to left
                vertex.addEdge(vertexId, ("V"+(i-1)));
                // vertex to right
                vertex.addEdge(vertexId, ("V"+(i+1)));
                // vertex below 
                vertex.addEdge(vertexId, ("V"+(i+numOfCols))); 
            } // end if/else chain

            // now that proper edges have been placed put the vertex in the hashmap
            this.vertices.put(vertexId, vertex);

        } // end for loop
    } // end buildGridGraph

    // not sure if working correctly
    public void createMazeWithDFS(String currentVertexId){

        // store the current vertex
        Vertex currentVertex = this.vertices.get(currentVertexId);

        boolean keepGoing = true;
        while(keepGoing){

            // meaning all the vertices have been visited
            if(this.someVerticesNotVisited() == false){
                keepGoing = false;
            // otherwise there are still some vertices that have not been visited yet
            } else {
                // visit the current vertex
                currentVertex.visit();
                // go through the edges on this vertex and randomly choose on that isn't already open
                // take the id of the destination vertex for the edge and store it
                String destinationVertexId = currentVertex.getRandomClosedPath();

                // if there are still closed paths to choose
                if(!destinationVertexId.equals("all paths open")){
                    // put the id of the current vertex on the stack
                    this.visitedVertices.push(currentVertexId);
                    // open path from this vertex to the destination vertex
                    currentVertex.toggleEdgePathOpen(currentVertexId, destinationVertexId);
                    // grab and store the destination vertex
                    Vertex destVertex = this.vertices.get(destinationVertexId);
                    // open a path from the destination vertex to the current vertex
                    // now the path should be bidirectional
                    destVertex.toggleEdgePathOpen(destinationVertexId, currentVertexId);

                    // make recursive call using destination vertex
                    createMazeWithDFS(destinationVertexId);

                // otherwise there are no closed paths to open
                } else {
                    // if there is something on the stack
                    if(!this.visitedVertices.isEmpty()){
                        // grab the id stored on the top of the stack
                        String poppedId = this.visitedVertices.pop();
                        // get the vertex associated with the id
                        Vertex poppedVertex = this.vertices.get(poppedId);
                        // backtrack by setting the current vertex to whatever was just pulled off the stack
                        currentVertex = poppedVertex;
                    }
                } // end if/else
            } // end else
            
        } // end while loop

    } // end createMazeWithDFS

    public boolean someVerticesNotVisited(){
        // iterate through each vertex in the vertices hashmap
        for(Map.Entry<String, Vertex> vertex : this.vertices.entrySet()){
            // if one of the vertices hasn't been visited
            if(vertex.getValue().isVisited() == false){
                return true;
            }
        }
        return false;
    } // end areAllVerticesVisited

    public static void main(String[] args){
        Graph g = new Graph(4, 4);
        g.createMazeWithDFS("V0");


        // iterate through the vertices using for each loop
        for(Map.Entry<String, Vertex> vertex : g.vertices.entrySet()){
            System.out.println("Vertex: " + vertex.getKey());
            System.out.println("Visited: " + vertex.getValue().isVisited());
            vertex.getValue().getEdgesOverview();
            System.out.println();
        }

    } // end main

} // end Graph
