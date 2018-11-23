public class Edge {
    private String startVertexId;
    private String endVertexId;
    private boolean pathOpen = false;

    public Edge(String startVertexId, String endVertexId){
        this.startVertexId = startVertexId;
        this.endVertexId = endVertexId;
    } // end constructor

    public void setData(String startVertexId, String endVertexId){
        this.startVertexId = startVertexId;
        this.endVertexId = endVertexId;
    } // end setData

    public String getData(){
        String data = "startVertexId: " + this.startVertexId;
        data += "\nendVertexId: " + this.endVertexId;
        data += "\npathOpen: " + this.pathOpen;
        return data;
    } // end getData

    public String getStartVertexId(){
        return this.startVertexId;
    } // getStartVertex

    public String getEndVertexId(){
        return this.endVertexId;
    } // end getEndVertex

    public boolean isPathOpen(){
        return this.pathOpen;
    } // end isPathOpen

    public void openPath(){
        this.pathOpen = true;
    } // end openPath

    public static void main(String[] args){
        Edge e = new Edge("V0", "V1");
        System.out.println(e.getData());
    } // end main

}// end Edge