import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class Graph {
    protected HashMap<String, Vertex> vertices = new HashMap<>();
    // stack for backtracking
    protected StringStack visitedVertices = new StringStack();

    public void createMazeWithDFS(String currentVertexId){
        // algorithm will end when all adjacent vertices are visited and it can't find anything unvisited by backtracking

        // store the current vertex
        Vertex currentVertex = this.vertices.get(currentVertexId);
        // mark the current vertex as visited
        currentVertex.visit();

        // get an ArrayList strings containing the ids for all the adjacent vertices
        ArrayList<String> allAdj = currentVertex.getAllAdjacentEndVertices();
        ArrayList<Vertex> allUnvisitedAdj = new ArrayList<Vertex>();
        // go through and find all the adjacent vertices that haven't been visited yet
        for(int i=0; i < allAdj.size(); i++){
            Vertex myVertex = this.vertices.get(allAdj.get(i));
            if(myVertex.isVisited() == false){
                allUnvisitedAdj.add(myVertex);
            }
        }

        Vertex currentEndVertex = null;
        // if there are unvisited adjacent vertices then randomly choose one
        if(!allUnvisitedAdj.isEmpty()){
            Random rand = new Random();
            int randInt = rand.nextInt(allUnvisitedAdj.size());
            currentEndVertex = allUnvisitedAdj.get(randInt);
        }

        // if an unvisited adjacent vertex was choosen
        if(currentEndVertex != null){
            // push the current vertex id to the stack
            this.visitedVertices.push(currentVertex.getId());
            // open the path from this vertex to the next
            currentVertex.openEdgePath(currentVertexId, currentEndVertex.getId());
            // open the path back the other way, from the end vertex to this vertex
            currentEndVertex.openEdgePath(currentEndVertex.getId(), currentVertexId);
            // make recursive call so that algorithm will move to visit the end vertex that was choosen
            createMazeWithDFS(currentEndVertex.getId());
        // otherwise all the adjacent vertices have already been visited
        // this means we are at a dead end and must backtrack
        } else{
            // if there is something left on the stack
            if(!this.visitedVertices.isEmpty()){
                // grab the id stored on the top of the stack
                String poppedId = this.visitedVertices.pop();
                // backtrack by sending the algorithm back to the most recent vertex that was visited
                createMazeWithDFS(poppedId);
            }
        } // end if/else

    } // end createMazeWithDFS

} // end Graph
