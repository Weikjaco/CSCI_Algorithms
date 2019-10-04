package recursionoutlab;

/**
 * @author Jacob Weikert
 * 04/7/2018
 * CSCI_132
 */

public class RecursionOutLab {
    void printMaze(char [][]mz){
        int x,y;
        for (x = 0; x < mz.length; ++x){
            for(y = 0; y < mz.length; ++y){
                System.out.print(mz[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    void maze(char[][] mz, int p_row, int p_col, int h_row, int h_col){  
        //The maze is displayed each step
        //Where person walked is marked with 'X'
        //Person is marked with a 'O'
        //End of Maze is marked with an F
        //Player started facing East
        
        if (mz[p_row][p_col] == 'F'){
            //Base case -End maze
            printMaze(mz);
            System.out.println("Maze Successfully Completed!");
        } else {
            if (h_row-1 == p_row){
                //If facing East
                if(mz[h_row][h_col] != '#'){ //If right turn available
                    //Update player
                    mz[p_row][p_col] = 'X'; 
                    p_row += 1;
                    p_col += 0;
                    if (mz[p_row][p_col] == 'F'){
                        mz[p_row][p_col] = 'F';
                    } else {
                        mz[p_row][p_col] = 'O';
                    }
                    //Update hand
                    h_row += 0;
                    h_col -= 1;
                    printMaze(mz);  
                } else { 
                    //If hand position is a wall check in front of player
                    if(mz[p_row][p_col+1] != '#'){
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row += 0;
                        p_col += 1;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 0;
                        h_col += 1;
                        printMaze(mz);
                    } else if (mz[p_row-1][p_col] != '#'){
                        //Turn left 90 and if not a wall move up
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row -= 1;
                        p_col += 0;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row -= 2;
                        h_col += 1;
                        printMaze(mz);
                    } else {
                        //Turn around
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row += 0;
                        p_col -= 1;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row -= 2;
                        h_col -= 1;
                        printMaze(mz);
                    }
                }
            } else if (h_row+1 == p_row){
                //If facing West
                if(mz[h_row][h_col] != '#'){
                    //Look right
                    mz[p_row][p_col] = 'X'; 
                    p_row -= 1;
                    p_col += 0;
                    if (mz[p_row][p_col] == 'F'){
                        mz[p_row][p_col] = 'F';
                    } else {
                        mz[p_row][p_col] = 'O';
                    }
                    //Update hand
                    h_row -= 0;
                    h_col += 1;
                    printMaze(mz);
                } else { 
                    //If hand position is a wall check in front of player
                    if(mz[p_row][p_col-1] != '#'){
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row += 0;
                        p_col -= 1;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 0;
                        h_col -= 1;
                        printMaze(mz);
                    } else if (mz[p_row+1][p_col] != '#'){
                        //Turn left 90 and if not a wall move up
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row += 1;
                        p_col -= 0;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 2;
                        h_col -= 1;
                        printMaze(mz);
                    } else {
                        //Turn around
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row -= 0;
                        p_col += 1;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 2;
                        h_col += 1;
                        printMaze(mz);
                    }
                }
                
            } else if (h_col -1 == p_col){
                //If facing North
                if(mz[h_row][h_col] != '#'){ 
                    //Take right if available
                    //Update player
                    mz[p_row][p_col] = 'X'; 
                    p_row += 0;
                    p_col += 1;
                    if (mz[p_row][p_col] == 'F'){
                        mz[p_row][p_col] = 'F';
                    } else {
                        mz[p_row][p_col] = 'O';
                    }
                    //Update hand
                    h_row += 1;
                    h_col -= 0;
                    printMaze(mz);  
                } else {
                    //If hand position is a wall check in front of player
                    if(mz[p_row-1][p_col] != '#'){
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row -= 1;
                        p_col += 0;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row -= 1;
                        h_col += 0;
                        printMaze(mz);
                    } else if (mz[p_row][p_col-1] != '#'){
                        //Turn left 90 and if not a wall move up
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row -= 0;
                        p_col -= 1;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row -= 1;
                        h_col -= 2;
                        printMaze(mz);
                    } else {
                        //Turn around
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row -= 1;
                        p_col += 0;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 1;
                        h_col -= 2;
                        printMaze(mz);
                    }
                }
            } else if (h_col+1 == p_col){
                //If facing South
                if(mz[h_row][h_col] != '#'){
                    //Look right
                    mz[p_row][p_col] = 'X'; 
                    p_row -= 0;
                    p_col -= 1;
                    if (mz[p_row][p_col] == 'F'){
                        mz[p_row][p_col] = 'F';
                    } else {
                        mz[p_row][p_col] = 'O';
                    }
                    //Update hand
                    h_row -= 1;
                    h_col += 0;
                    printMaze(mz);
                } else { 
                    //If hand position is a wall check in front of player
                    if(mz[p_row+1][p_col] != '#'){
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row += 1;
                        p_col += 0;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 1;
                        h_col += 0;
                        printMaze(mz);
                    } else if (mz[p_row][p_col+1] != '#'){
                        //Turn left 90 and if not a wall move up
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row += 0;
                        p_col += 1;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 1;
                        h_col += 2;
                        printMaze(mz);
                    } else {
                        //Turn around
                        //Update player
                        mz[p_row][p_col] = 'X';
                        p_row -= 0;
                        p_col += 1;
                        if (mz[p_row][p_col] == 'F'){
                            mz[p_row][p_col] = 'F';
                        } else {
                            mz[p_row][p_col] = 'O';
                        }
                        //Update hand
                        h_row += 2;
                        h_col += 1;
                        printMaze(mz);
                    }
                }
                
            }
            maze(mz, p_row, p_col, h_row, h_col); 
        }
    }

    public static void main(String[] args) {
        //Get char array for maze ("In this lab its given")
        char [][] strtMaze = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#'},
                {'.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#'},
                {'#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#'},
                {'#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '#'},
                {'#', '#', '#', '#', '.', '#', 'F', '#', '.', '#', '.', '#'},
                {'#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
                {'#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        };
        //Get start position of person
        int pstartx = 2;
        int pstarty = 0;
        //Get start position of hand
        int hstartx = 3; //If same as pstart(facing north south)
        int hstarty = 0;//If same as pstart (facing east west)
        RecursionOutLab maze1 = new RecursionOutLab();
        maze1.maze(strtMaze, pstartx, pstarty, hstartx, hstarty);
    }
    
}
