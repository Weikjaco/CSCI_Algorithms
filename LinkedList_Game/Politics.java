/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Takes in a valid file and reads in the number of applicants (N), selector ones
 * number (k), and selectors two numbers (m). The selectors will loop around the
 * linked list - looping an amount of nodes based on the numbers given to them.
 * If the selectors land on the same node, the node becomes a politician. 
 * Otherwise the two nodes are eliminated from the linked list.
 */
package politics;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author Jacob Weikert
 *         3/22/2018
 *         CSCI 132
 */
public class Politics {
    Scanner inName;
    Scanner in;
    LinkedList list;
    
    Politics(){
        list = new LinkedList(); 
    }
    
    void start(){
        //Get File
        inName = new Scanner(System.in);
        System.out.println("Please enter a valid input file (with '.txt').");
        String file = inName.next();
        try {
            in = new Scanner(new File(file));
        } catch(FileNotFoundException e) {
            System.err.println("Error, File Not Found!");
        }
        
        //Declare Variables for file read in format
        String line;
        String[] words;
        int i;
        
        try {
            //Create output file
            File file_out = new File("LinkedListProgram.txt");
            
            if (file_out.createNewFile()){
               System.out.println("File LinkedListProgram.txt is created!");
            } else {
               System.out.println("File already exists");
            }
            FileWriter writer = new FileWriter("LinkedListProgram.txt");
            //Read in file and throw into words array
            while(in.hasNextLine()){
                line = in.nextLine();
                words = line.split(" ");
                for (i = 0; i < words.length-1; i++){
                    words[i] = words[i].trim();
                }
                Integer numNodes = new Integer(words[0]); //Input is id for amnt of Nodes
                Integer k = new Integer(words[1]);
                Integer m = new Integer(words[2]);
                for (i = 0; i < numNodes; ++i){
                    list.add(i+1);
                }
                list.play(k, m, numNodes, writer);
                list.deleteList();
            }
            writer.write("End of Program 4");
            System.out.println("End of Program 4");
            writer.write(System.lineSeparator());
            writer.close();
        } catch (Exception e) {
            System.err.println("Error!");
        }
    }
    public static void main(String[] args) {
        Politics Poli = new Politics();
        Poli.start();
    }
}
