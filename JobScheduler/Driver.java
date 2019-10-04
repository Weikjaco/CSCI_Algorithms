/*
 * Jacob Weikert
 * Lab 1 February 8th 2019
 * CSCI 232
 */
package jobscheduler;
import java.io.*;
import java.util.*;


public class JobScheduler {
    //Initialization of static properties
    static int totalJobs = 0;
    static int arrSize = 10;
    static Job[] jobArray = new Job[arrSize];
    static int time = 0;
    static int totalQueueTime = 0;
    //--------------------------------------------------------------------------//
    public Job[] sortArrivalTime(Job[] jobArray){
        //Sort Input File based on arrival time of job objects (Bubble sort-sorry)
        for (int x=0;x<totalJobs;x++){
            for (int y=x+1;y<totalJobs;y++){
                if (jobArray[x].getArrivalTime() < jobArray[y].getArrivalTime()){
                    Job temp = jobArray[y];
                    jobArray[y] = jobArray[x];
                    jobArray[x] = temp;
                }
            }    
        }
        return jobArray;
    }
    //--------------------------------------------------------------------------//
    public Job[] getMemoryForArr(Job[] arr){
        //Allocate more memory when needed
        arrSize *= 2;
        Job[] temp = new Job[arrSize];
        for (int x=0;x<arrSize;++x){
            temp[x] = arr[x];
        }
        return temp;
    }
    //--------------------------------------------------------------------------//
    public Job[] getFile(String userFile){
        //Ensure file exists in pathed directory, read in and store each Job object in 
        try {
            //If no exception is thrown, then Read in & Scan file. Store each job object in queue and sort by arrival time.
            FileReader readFile = new FileReader(userFile);
            Scanner scanFile = new Scanner(readFile);
            while (scanFile.hasNextLine()){
                String[] line = scanFile.nextLine().split(" ");
                int jobNum = Integer.parseInt(line[0]);int priority = Integer.parseInt(line[1]);
                int arrivalTime = Integer.parseInt(line[2]);int duration = Integer.parseInt(line[3]);
                totalQueueTime += duration;
                //Ensure array isnt full, if it is allocate more memory.
                if (totalJobs >= arrSize){
                    jobArray = getMemoryForArr(jobArray);
                    jobArray[totalJobs] = new Job(jobNum,priority,arrivalTime,duration);
                } else {
                    jobArray[totalJobs] = new Job(jobNum,priority,arrivalTime,duration);
                }
                //Update Total Jobs
                totalJobs += 1;
            }
            //Sort jobs based on arrival time.
            jobArray = sortArrivalTime(jobArray);
       } 
        catch(FileNotFoundException e){
            //If no file is found.
            System.out.println("Exception thrown: " + e);
       }
       return jobArray;
    }
    //--------------------------------------------------------------------------//
    public void processFile(int numOfProcessors){
        //Create number of processors as requested by user
        int numP = numOfProcessors;
        Processors[] pArr = new Processors[numP];
        for(int i=0;i<numP;i++){
            pArr[i] = new Processors(i+1);
        }
        //Execute when jobs available and queue time is greater than 0 - error
        while(totalJobs != 0 || totalQueueTime > (0)){            
            Processors shortestQueue = pArr[0];
            for (int j=0;j<numP;++j){
                //Delete Jobs that are finished (update second before delete)                
                pArr[j].jobCompletionCheck();
            }
            if (totalJobs > 0){
                //Add Jobs
                Job currentJob = jobArray[totalJobs-1];
                //If same arrival time for multiple Jobs
                while(time == currentJob.getArrivalTime() && totalJobs > 0){

                    //Store job in the processor with shortest Queue Time and add to queue size and queue time.
                    for(int j=0;j<numP;++j){
                        if (pArr[j].getQueueTime() < shortestQueue.getQueueTime()){
                            shortestQueue = pArr[j];
                        }
                    }
                    shortestQueue.addJob(currentJob);
                    shortestQueue.addOneToQueue(); //Should I keep this in the object?  Or show in the main class?????? Personal preference????
                    shortestQueue.addQueueTime(currentJob.getDuration());
                    //Update job Array.
                    jobArray[totalJobs-1] = null;
                    --totalJobs;
                    if (totalJobs > 0) {
                        currentJob = jobArray[totalJobs-1];
                    }
                }
                for (int j=0;j<numP;++j){
                    pArr[j].printQueue();
                }                
                time++;
            } else {
                for (int j=0;j<numP;++j){
                    pArr[j].printQueue();
                }
                time++;
            }
        }
    }
    
    
    
    
    
    
    //--------------------------------------------------------------------------//
    public static void main(String[] args) {
        //Request file name from user.
        Scanner inpFile = new Scanner(System.in);
        System.out.print("Please enter your job file name: ");
        String userFile = inpFile.nextLine();
        
        //Call getFile method to get the named file and get a list of the jobs sorted by arrival time.
        JobScheduler desiredFile = new JobScheduler();
        desiredFile.getFile(userFile);
        
        //Request how many processors the user would like to use and execute.
        Scanner pRequest = new Scanner(System.in);
        System.out.println("How many processors would you like executing your file?");
        int withNumP = pRequest.nextInt();
        //Process the file with desired processors
        desiredFile.processFile(withNumP);
    }
}
