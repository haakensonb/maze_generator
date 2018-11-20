public class Edge {
    private String startVertex;
    private String endVertex;
    private boolean pathOpen = false;

    public Edge(String startVertex, String endVertex){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    } // end constructor

    public void setData(String startVertex, String endVertex){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    } // end setData

    public String getData(){
        String info = "startVertex: " + this.startVertex;
        info += "\nendVertex: " + this.endVertex;
        info += "\npathOpen: " + this.pathOpen;
        return info;
    } // end getData

    public String getStartVertex(){
        return this.startVertex;
    } // getStartVertex

    public String getEndVertex(){
        return this.endVertex;
    } // end getEndVertex

    public boolean isPathOpen(){
        return this.pathOpen;
    } // end isPathOpen

    public void togglePath(){
        if(this.pathOpen == true){
            this.pathOpen = false;
        } else if (this.pathOpen == false){
            this.pathOpen = true;
        }
    } // end togglePath

    public void openPath(){
        this.pathOpen = true;
    } // end openPath

    public static void main(String[] args){
        Edge e = new Edge("V0", "V1");
        System.out.println(e.getData());
    } // end main

}// end Edge