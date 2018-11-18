public abstract class Node {
    // the node that will come after this one
    protected Node next;

    Node(Node next){
        this.next = next;
    } // end constructor

    public void setNext(Node next){
        this.next = next;
    } // end setNext

    public Node getNext(){
        return this.next;
    } // end getNext

} // end Node