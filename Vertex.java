import java.util.ArrayList;
import java.util.Random;

public class Vertex {
    private int x;
    private int y;
    private ArrayList<Edge> edges;

    public Vertex(){
        this.edges = new ArrayList<Edge>();
    } // end null parameter constructor

    public void addEdge(String startVertex, String endVertext){
        Edge edge = new Edge(startVertex, endVertext);
        this.edges.add(edge);
    } // end addEdge

    // randomly chooses a closed path and returns the vertex that path leads to
    public String getRandomClosedPath(){
        ArrayList<String> closedPaths = new ArrayList<String>();
        for(int i=0; i < this.edges.size(); i++){
            Edge currentEdge = this.edges.get(i);
            if(currentEdge.isPathOpen() == false){
                closedPaths.add(currentEdge.getEndVertex());
            }
        }
        if(closedPaths.isEmpty()){
            return "all paths open";
        } else {
            Random rand = new Random();
            int randInt = rand.nextInt(closedPaths.size());
            return closedPaths.get(randInt);
        }
    } // end getRandomClosedPath

    public void getEdgesOverview(){
        for(int i=0; i < this.edges.size(); i++){
            System.out.println(this.edges.get(i).getData());
        }
    } // end getEdgesOverview

    public static void main(String[] args){
        Vertex v1 = new Vertex();
        v1.addEdge("V1", "V2");
        v1.addEdge("V1", "V3");
        v1.addEdge("V1", "V4");
        for(int i=0; i < v1.edges.size(); i++){
            // open one of the paths
            if(i == 2){
                v1.edges.get(i).togglePath();
            }
            System.out.println(v1.edges.get(i).getData());
        }
        System.out.println("Choosing random closed path: " + v1.getRandomClosedPath());
    } // end main


} // end Vertex