public class Edge {
    private String startId;
    private String endId;
    private boolean pathOpen = true;

    public Edge(String startId, String endId){
        this.startId = startId;
        this.endId = endId;
    }

    public boolean isPathOpen(){
        return this.pathOpen;
    }

    public void togglePath(){
        if(this.pathOpen == true){
            this.pathOpen = false;
        } else if (this.pathOpen == false){
            this.pathOpen = true;
        }
    }

    public String getInfo(){
        String info = "startId: " + this.startId;
        info += "\nendId: " + this.endId;
        info += "\npathOpen: " + this.pathOpen;
        return info;
    }

    public static void main(String[] args){
        Edge e = new Edge("V1", "V2");
        System.out.println(e.getInfo());
    }


}// end Edge