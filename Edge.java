public class Edge extends Node<Edge> {
    private int startVertexIndex;
    private int endVertexIndex;
    private boolean pathOpen = true;

    public Edge(){
        super(null);
        this.startVertexIndex = 0;
        this.endVertexIndex = 0;
    } // end null parameter constructor

    public Edge(int startVertexIndex, int endVertexIndex, Edge next){
        super(next);
        this.startVertexIndex = startVertexIndex;
        this.endVertexIndex = endVertexIndex;
    } // end constructor

    public void setData(int startVertexIndex, int endVertexIndex){
        this.startVertexIndex = startVertexIndex;
        this.endVertexIndex = endVertexIndex;
    } // end setData

    public String getData(){
        String info = "startVertexIndex: " + this.startVertexIndex;
        info += "\nendVertexIndex: " + this.endVertexIndex;
        info += "\npathOpen: " + this.pathOpen;
        return info;
    } // end getData

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

    public static void main(String[] args){
        Edge e = new Edge(0, 1, null);
        System.out.println(e.getData());
        System.out.println("Next node: " + e.getNext());
    } // end main

}// end Edge