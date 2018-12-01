public class Player{
    private int x;
    private int y;
    private int width;
    private int height;
    private String currentVertexId;

    public Player(int x, int y, int width, int height, String currentVertexId){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentVertexId = currentVertexId;
    } // end constructor

    public int getX(){
        return this.x;
    } // end getX

    public int getY(){
        return this.y;
    } // end getY

    public int getWidth(){
        return this.width;
    } // end getWidth

    public int getHeight(){
        return this.height;
    } // end getHeight

    public String getCurrentVertexId(){
        return this.currentVertexId;
    } // end getCurrentVertexId

    public void setCurrentVertexId(String currentVertexId){
        this.currentVertexId = currentVertexId;
    } // end setCurrentVertexId

    public void movePlayer(int newX, int newY, String newVertexId){
        this.x = newX;
        this.y = newY;
        this.currentVertexId = newVertexId;
    } // end movePlayer

} // end Player