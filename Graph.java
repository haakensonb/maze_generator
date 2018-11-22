import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class Graph {
    protected HashMap<String, Vertex> vertices = new HashMap<>();
    // stack for backtracking
    protected StringStack visitedVertices = new StringStack();

    // not sure if working correctly
    public void createMazeWithDFS(String currentVertexId){

        // store the current vertex
        Vertex currentVertex = this.vertices.get(currentVertexId);

        currentVertex.visit();
        // this.visitedVertices.push(currentVertex.getId());

        ArrayList<String> allAdj = currentVertex.getAllEndVertices();
        ArrayList<Vertex> allUnvisitedAdj = new ArrayList<Vertex>();
        for(int i=0; i < allAdj.size(); i++){
            Vertex myVertex = this.vertices.get(allAdj.get(i));
            if(myVertex.isVisited() == false){
                allUnvisitedAdj.add(myVertex);
            }
        }
        Vertex currentEndVertex = null;
        if(!allUnvisitedAdj.isEmpty()){
            Random rand = new Random();
            int randInt = rand.nextInt(allUnvisitedAdj.size());
            currentEndVertex = allUnvisitedAdj.get(randInt);
        }

        // String currentEndVertexId = currentVertex.getRandomClosedPath();
        // Vertex currentEndVertex = this.vertices.get(currentEndVertexId);
        // if(currentEndVertex.isVisited() == false){
        if(currentEndVertex != null){
            this.visitedVertices.push(currentVertex.getId());
            currentVertex.toggleEdgePathOpen(currentVertex.getId(), currentEndVertex.getId());
            currentEndVertex.toggleEdgePathOpen(currentEndVertex.getId(), currentVertex.getId());
            createMazeWithDFS(currentEndVertex.getId());
        } else{
            if(!this.visitedVertices.isEmpty()){
                // grab the id stored on the top of the stack
                String poppedId = this.visitedVertices.pop();
                // get the vertex associated with the id
                Vertex poppedVertex = this.vertices.get(poppedId);
                // backtrack by setting the current vertex to whatever was just pulled off the stack
                // currentVertex = poppedVertex;
                createMazeWithDFS(poppedId);
            }
        }


        // boolean keepGoing = true;
        // while(keepGoing){

        //     // meaning all the vertices have been visited
        //     if(this.someVerticesNotVisited() == false){
        //         keepGoing = false;
        //     // otherwise there are still some vertices that have not been visited yet
        //     } else {
        //         // visit the current vertex
        //         currentVertex.visit();
        //         // go through the edges on this vertex and randomly choose on that isn't already open
        //         // take the id of the destination vertex for the edge and store it
        //         String destinationVertexId = currentVertex.getRandomClosedPath();

        //         // if there are still closed paths to choose
        //         if(!destinationVertexId.equals("all paths open")){
        //             // put the id of the current vertex on the stack
        //             this.visitedVertices.push(currentVertexId);
        //             // open path from this vertex to the destination vertex
        //             currentVertex.toggleEdgePathOpen(currentVertexId, destinationVertexId);
        //             // grab and store the destination vertex
        //             Vertex destVertex = this.vertices.get(destinationVertexId);
        //             // open a path from the destination vertex to the current vertex
        //             // now the path should be bidirectional
        //             destVertex.toggleEdgePathOpen(destinationVertexId, currentVertexId);

        //             // make recursive call using destination vertex
        //             createMazeWithDFS(destinationVertexId);

        //         // otherwise there are no closed paths to open
        //         } else {
        //             // if there is something on the stack
        //             if(!this.visitedVertices.isEmpty()){
        //                 // grab the id stored on the top of the stack
        //                 String poppedId = this.visitedVertices.pop();
        //                 // get the vertex associated with the id
        //                 Vertex poppedVertex = this.vertices.get(poppedId);
        //                 // backtrack by setting the current vertex to whatever was just pulled off the stack
        //                 currentVertex = poppedVertex;
        //             }
        //         } // end if/else
        //     } // end else
            
        // } // end while loop

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


} // end Graph
