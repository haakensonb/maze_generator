public class EdgeLinkedList {
    private int numOfNodes = 0;
    public Edge head;

    public EdgeLinkedList(){
        this.head = new Edge();
    } // end constructor

    public void traverse(){
        // store reference to first node in list
        Edge currentNode = head;
        // as long as the current edge we are looking at is not empty
        while(currentNode != null){
            // print info about edge
            System.out.println(currentNode.getData());
            System.out.println("\n");
            // set the current node to the one that comes after it in the list
            currentNode = currentNode.getNext();
        }
    } // end traverse

    public void addToEnd(Edge edge){
        // store reference to first node in the list
        Edge currentNode = head;
        // as long as there is a node after the current node
        while(currentNode.getNext() != null){
            // set the current node to the next node
            currentNode = currentNode.getNext();
        }
        // now the current node should be the last one in the list
        // so make the node after the current node our new edge node
        currentNode.setNext(edge);
        // update the number of nodes in the list
        this.numOfNodes += 1;
    } // end addToEnd

    public static void main(String[] args){
        EdgeLinkedList ell = new EdgeLinkedList();
        ell.traverse();
        System.out.println("Adding to end and then travsersing again...");
        Edge edge = new Edge();
        ell.addToEnd(edge);
        ell.traverse();
    } // end main

} // end EdgeLinkedList