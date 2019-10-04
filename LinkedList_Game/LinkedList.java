package politics;
import java.io.FileWriter;
import java.io.IOException;
public class LinkedList {
    Node first;
    Node tail;
    Node k;
    Node m;
    
    LinkedList() {
        first = null;
        tail = first;
    }
    boolean isEmpty(){
        return (first == null);
    }
    //Add amount of nodes specified in input file
    void add(int newNodeID) {
        Node n = new Node(newNodeID);
        if (isEmpty()){
            first = n;
        } else {
            n.setPrev(tail);
            tail.setNext(n);
        }
        //Make a circular linked list
        tail = n;
        tail.setNext(first);
        first.setPrev(tail);
    }
    //Play the game
    void play(int k_value, int m_value, int numNodes, FileWriter writer) throws IOException{
        k = first;
        m = tail;
        int tempk,tempm;
        //Print header
        writer.write("Program 4");
        System.out.println("Program 4");
        writer.write(System.lineSeparator());
        writer.write("---------");
        System.out.println("---------");
        writer.write(System.lineSeparator());
        System.out.println("");
        writer.write(System.lineSeparator());
        writer.write("N = " + numNodes + ", k = " + k_value + ", m = " + m_value);
        System.out.println("N = " + numNodes + ", k = " + k_value + ", m = " + m_value);
        writer.write(System.lineSeparator());
        System.out.println("");
        writer.write(System.lineSeparator());
        writer.write("Output");
        System.out.println("Output");
        writer.write(System.lineSeparator());
        writer.write("------");
        System.out.println("------");
        writer.write(System.lineSeparator());
        //while game is not done
        while (first != null){
            //rotate k once
            for (int i = 1; i < k_value ; ++i){
                k = k.getNext(); //Move k k times cw
            }
            //rotate m once
            for (int i = 1; i < m_value ; ++i){
                m = m.getPrev(); //Move m m times cc
            }
            tempk = k.getID();
            tempm = m.getID();
            
            if (tempk != first.getID() && tempm != first.getID()) {
                //Remove the nodes selected
                if (k.getID() != m.getID()){
                    writer.write(tempk + " " + tempm);
                    System.out.println(tempk + " " + tempm);
                    writer.write(System.lineSeparator());
                    //remove k node and connect
                    k = k.getNext();
                    k.setPrev(k.getPrev().getPrev());
                    k.getPrev().setNext(k);
                    if (k.getID() == m.getID()){
                        k = k.getNext();
                    }
                    //remove m node and connect
                    m = m.getPrev();
                    m.getNext().getNext().setPrev(m);
                    m.setNext(m.getNext().getNext());
                } else {
                    writer.write("" + tempk);
                    System.out.println(tempk);
                    writer.write(System.lineSeparator());
                    k = k.getNext();
                    m = m.getPrev();
                    m.setNext(k);
                    k.setPrev(m);
                }
            } else if (tempk == first.getID() || tempm == first.getID()){
                //** If two nodes left and k and m are pointing to different nodes**
                if ((k.getNext().getID() == m.getID()) && (m.getNext().getID() == k.getID())){
                    first = null;
                } else {
                    do{
                        first = first.getNext();
                    } while(first.getID() == tempk || first.getID() == tempm);
                }
                
                //Remove the nodes selected
                if (k.getID() != m.getID()){
                    writer.write(tempk + " " + tempm);
                    System.out.println(tempk + " " + tempm);
                    writer.write(System.lineSeparator());
                    //remove k node and connect
                    k = k.getNext();
                    k.setPrev(k.getPrev().getPrev());
                    k.getPrev().setNext(k);
                    if (k.getID() == m.getID()){
                        k = k.getNext();
                    }
                    //remove m node and connect
                    m = m.getPrev();
                    m.getNext().getNext().setPrev(m);
                    m.setNext(m.getNext().getNext());
                } else {
                    writer.write("" + tempk);
                    System.out.println(tempk);
                    writer.write(System.lineSeparator());
                    k = k.getNext();
                    m = m.getPrev();
                    m.setNext(k);
                    k.setPrev(m);
                }
            } 
        }
        writer.write(System.lineSeparator());
        System.out.println("");
    }
    //Delete list
    void deleteList() {
        first = null;
    }
}
