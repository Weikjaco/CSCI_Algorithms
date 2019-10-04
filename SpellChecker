package spellcheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Jacob Weikert
 * Reads in a file and searches for misspelled words. If a word is misspelled multiple algorithms are used to suggest
 * a word the user could of meant.
 */
public class SpellCheck {
    public void newWordSuggestions(String word){
        
        
    }
    public static void main(String[] args) {
        SpellCheck sp1 = new SpellCheck();
        String listOfWordsFile = "words.txt";
        String doc = "mydoc.txt";
        String line;
        String[] splitLine;
        String[] splitLineforPunctuation;
        ArrayList<String> words = new ArrayList();
        
        try {
            FileReader fileReader = new FileReader(listOfWordsFile);
            BufferedReader bufferReader = new BufferedReader(fileReader); //Read Dictionary
            FileReader fileReaderTwo = new FileReader(doc);
            BufferedReader bufferReaderTwo = new BufferedReader(fileReaderTwo); //Read Document
            //Store dictionary
            while((line = bufferReader.readLine()) != null){
                words.add(line);
            }
            
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("mydoc-checked.txt"));
                //Read in Document
                while((line = bufferReaderTwo.readLine()) != null){
                    splitLine = line.split(" ");
                    for (int i=0;i<splitLine.length;i++){
                        if(!words.contains(splitLine[i].toLowerCase())){
                            //If word is not in dictionary split again to get rid of punctuation
                            splitLineforPunctuation = splitLine[i].split("[|,|.|?|)|(|_|=|+|||)|!|@|#|$|%|^|*|:|;|>|<|`|~| |]");
                            String word = splitLineforPunctuation[0];
                            if(!words.contains(word.toLowerCase())){
                                //If it's still not in dictionary it is deemed "misspelled"
                                //Make suggestions -- based on user input add to new document
                                int cntOptions=1;
                                String[] wordFlag = new String[4];
                                Scanner reader = new Scanner(System.in);
                                System.out.println();
                                System.out.println("\"" + word + "\"" + " - Did you mean:");
                                //------------------------------------------------------------
                                //Grab Non-word Errors
                                for(int c=0;c<word.length();c++){
                                    //Remove 1 letter -- see if in dictionary
                                    StringBuilder sb = new StringBuilder(word);
                                    String nonWord;
                                    nonWord = sb.deleteCharAt(c).toString();
                                    if(!words.contains(nonWord.toLowerCase())){
                                        //If this was not the mistake then pass
                                    } else {
                                        //Else suggest new word
                                        System.out.println(cntOptions + ". " + sb);
                                        wordFlag[cntOptions-1] = nonWord;
                                        cntOptions++;
                                        break;
                                    }
                                }
                                //Grab Scrambled error
                                char[] ss = word.toCharArray();
                                outerloop:
                                for(int c=0;c<word.length();c++){
                                    for(int j=1;j<word.length();j++){
                                        //Perform swap
                                        char temp = ss[c];
                                        ss[c] = ss[j];
                                        ss[j] = temp;
                                        String scramWord = new String(ss);
                                        if(!words.contains(scramWord.toLowerCase())){
                                            //If this was not the mistake then pass
                                        } else {
                                            //Else suggest new word
                                            System.out.println(cntOptions + ". " + scramWord);
                                            wordFlag[cntOptions-1] = scramWord;
                                            cntOptions++;
                                            break outerloop;
                                        }
                                    }
                                }
                                //Different last letter
                                char[] ls = word.toCharArray();
                                for(int c=0;c<26;c++){
                                    ls[word.length()-1] = (char)(97+c); //97-122
                                    String diffLastChar = new String(ls);
                                    if(!words.contains(diffLastChar.toLowerCase())){
                                        //If this was not the mistake then pass
                                    } else {
                                        //Else suggest new word
                                        System.out.println(cntOptions + ". " + diffLastChar);
                                        wordFlag[cntOptions-1] = diffLastChar;
                                        cntOptions++;
                                        break;
                                    }
                                }
                                
                                //SomethingElse Error Option
                                System.out.println(cntOptions + ". Something else...");
                                //Get User's choice
                                System.out.println("Please Enter:");
                                int usersChoice = reader.nextInt();
                                String newWord = wordFlag[usersChoice-1];
                                //Add new word to document
                                if(newWord == null){
                                    Scanner stElse = new Scanner(System.in);
                                    System.out.println("What word did you mean?");
                                    newWord = stElse.nextLine();
                                    out.write(newWord + " ");
                                } else {
                                    out.write(newWord + " ");
                                }
                                //reader.close();
                                
                            } else {
                                //Add to document
                                out.write(splitLine[i] + " ");
                            }
                        } else {
                            //Add to document
                            out.write(splitLine[i] + " ");
                        }
                    }
                }
                out.close();
                System.out.println("mydoc-checked.txt created successfully");
            } catch (IOException e) {
            }
            
        } catch(FileNotFoundException ex){
                System.out.println("Unable to find file: " + listOfWordsFile + "/or/" + doc);
        } catch(IOException ex){
                System.out.println("Error reading in file: " + listOfWordsFile);
        }
    }
    
}
