// use generic because Node might be different object type than just Node
public abstract class Node<T> {
    // the node that will come after this one
    protected T next;

    Node(T next){
        this.next = next;
    } // end constructor

    public void setNext(T next){
        this.next = next;
    } // end setNext

    public T getNext(){
        return this.next;
    } // end getNext

} // end Node