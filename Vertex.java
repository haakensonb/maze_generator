import java.util.ArrayList;

public class Vertex {
    private int x;
    private int y;
    private boolean visited;
    private String vertexId;
    private ArrayList<Edge> edges;

    public Vertex(String vertexId, int x, int y){
        this.x = x;
        this.y = y;
        this.vertexId = vertexId;
        this.visited = false;
        this.edges = new ArrayList<Edge>();
    } // end constructor

    public String getId(){
        return this.vertexId;
    } // end getId

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void visit(){
        if(this.visited == false){
            this.visited = true;
        }
    } // end visit 

    public boolean isVisited(){
        return this.visited;
    } // end isVisited

    public void addEdge(String startVertexId, String endVertextId){
        Edge edge = new Edge(startVertexId, endVertextId);
        this.edges.add(edge);
    } // end addEdge

    public ArrayList<String> getAllAdjacentOpenPaths(){
        ArrayList<String> endVertices = new ArrayList<String>();
        for(int i=0; i < this.edges.size(); i++){
            String endId;
            Edge currentEdge = this.edges.get(i);
            if(currentEdge.isPathOpen()){
                endId = currentEdge.getEndVertexId();
                endVertices.add(endId);
            }
        }
        return endVertices;
    } // end getAllAdjacentOpenPaths

    public ArrayList<String> getAllAdjacentEndVertices(){
        ArrayList<String> endVertices = new ArrayList<String>();
        for(int i=0; i < this.edges.size(); i++){
            String endId;
            Edge currentEdge = this.edges.get(i);
            endId = currentEdge.getEndVertexId();
            endVertices.add(endId);
        }
        return endVertices;
    } // end getAllAdjacentEndVertices

    // not the most efficient way
    public void openEdgePath(String startVertexId, String endVertexId){
        for(int i=0; i < this.edges.size(); i++){
            Edge currentEdge = this.edges.get(i);
            // if the current edge matches the parameters then it is the one we are looking for
            if(currentEdge.getStartVertexId().equals(startVertexId) && currentEdge.getEndVertexId().equals(endVertexId)){
                currentEdge.openPath();
            }
        }
    } // end openEdgePath

    public void getEdgesOverview(){
        for(int i=0; i < this.edges.size(); i++){
            System.out.println(this.edges.get(i).getData());
        }
    } // end getEdgesOverview

    public static void main(String[] args){
        Vertex v1 = new Vertex("V1", 0, 0);
        v1.addEdge("V1", "V2");
        v1.addEdge("V1", "V3");
        v1.addEdge("V1", "V4");
        for(int i=0; i < v1.edges.size(); i++){
            // open one of the paths
            if(i == 2){
                v1.edges.get(i).openPath();
            }
            System.out.println(v1.edges.get(i).getData());
        }
    } // end main

} // end Vertex