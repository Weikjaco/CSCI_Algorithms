
package politics;

public class Node {
    private int id;
    private Node next; //initially null
    private Node prev;
    
    Node(int id){
        this.id = id;
    }
    
    void setNext(Node temp){
        next = temp;
    }
    
    void setPrev(Node temp){
        prev = temp;
    }
    
    Node getNext(){
        return next;
    }
    
    Node getPrev() {
        return prev;
    }
  
    int getID(){
        return this.id;
    }
}  
