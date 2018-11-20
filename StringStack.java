public class StringStack {
    private Node<String> topOfStack;

    public StringStack(){
        // nothing on the stack
        this.topOfStack = null;
    } // end constructor

    public void push(String data){
        Node<String> newNode = new Node<String>(data, topOfStack);
        this.topOfStack = newNode;
    } // end push

    public String pop(){
        String value;
        if(this.topOfStack == null){
            value = "stack empty";
        } else {
            value = this.topOfStack.getData();
            this.topOfStack = this.topOfStack.getNext();
        }
        return value;
    } // end pop

    public static void main(String[] args){
        StringStack s = new StringStack();
        // should be empty
        System.out.println(s.pop());
        s.push("V0");
        s.push("V1");
        System.out.println(s.pop());
        System.out.println(s.pop());
    } // end main

} // end Stack