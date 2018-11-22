import java.util.Map;

public class GridGraph extends Graph {

    public GridGraph(int numOfRows, int numOfCols){
        this.buildGridGraph(numOfRows, numOfCols);

    } // end constructor

    public void buildGridGraph(int numOfRows, int numOfCols){
        int totalVertices = numOfRows * numOfCols;
        int currentRow = 0;
        int x = 0;
        int y = 0;
        for(int i=0; i < totalVertices; i++){
            // increment the current row
            if (((i % numOfCols) == 0) && (i != 0)){
                currentRow += 1; 
                y += 50;
                x = 0;
            }

            String vertexId = "V" + i;
            Vertex vertex = new Vertex(vertexId, x, y);

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
            x += 50;
            // y += 35;
        } // end for loop
    } // end buildGridGraph

    public static void main(String[] args){
        GridGraph g = new GridGraph(4, 4);
        g.createMazeWithDFS("V0");


        // iterate through the vertices using for each loop
        for(Map.Entry<String, Vertex> vertex : g.vertices.entrySet()){
            System.out.println("Vertex: " + vertex.getKey());
            System.out.println("Visited: " + vertex.getValue().isVisited());
            vertex.getValue().getEdgesOverview();
            System.out.println();
        }

    } // end main

} // end GridGraph