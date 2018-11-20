// use generic because Node might need to take different data types 
// but in this program it will probably always just be a String
public class Node<T> {
    // the node that will come after this one
    private Node<T> next;
    private T data;

    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    } // end constructor

    public T getData(){
        return this.data;
    } // end getData

    public void setData(T data){
        this.data = data;
    } // end setData

    public void setNext(Node<T> next){
        this.next = next;
    } // end setNext

    public Node<T> getNext(){
        return this.next;
    } // end getNext

    public static void main(String[] args){
        Node<String> n = new Node<String>("V0", null);
        // getData may not always return String
        System.out.println(n.getData());
    } // end main

} // end Node