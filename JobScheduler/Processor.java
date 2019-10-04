package jobscheduler;
/**
 * @author Jacob
 */
public class Processors {
    private int queueSize = 0; //How many objects in queue.
    private int QueueTime = 0; //Processing Time for queue (using this to find "shortest queue")
    final int processorNum; //Processor number (will never change)
    Job[] heap; //Our priority queue
    
    Processors(int i){
        //When processor is created have a queue length equal to total jobs
        heap = new Job[JobScheduler.totalJobs+1];
        processorNum = i;
    }
    
    public void addQueueTime(int queueTime){
        //Used for sorting the shortest queue time in JobScheduler
        QueueTime += queueTime;
    }
    
    public void addJob(Job job){
        //Define root -- we skip index 0 cell for the convenience of implementation
        int idx = queueSize+1;
        //If queue is empty add to index 1.
        if (queueSize == 0){
            heap[idx] = job;//Insert at end of array
        } else {
            //Compare Priorities in queue and sort.
            heap[idx] = job;
            int parent = idx/2;
            while (true){
                if (heap[idx].getPriority() > heap[parent].getPriority()){
                    Job temp = heap[parent];
                    heap[parent] = heap[idx];
                    heap[idx] = temp;
                    idx = parent;
                    if (parent == 1){break;} else {parent = idx/2;}
                } else {
                    break;
                }
            }
        }
    }
    
    public void jobCompletionCheck(){
        //Initialize heap variables for processor
        int idx = queueSize;

        //Find wait time
        if (heap[1] != null){
            if ((heap[1].getStartDuration() == heap[1].getDuration()) && heap[1].getFlag() == true){
                heap[1].setWhenStarted(JobScheduler.time-1);
                heap[1].setFlag(); //Flag is to ensure this does not update after being set.
            }
            //Subtract one second from duration of the queue head object
            heap[1].subtractOneDuration();
            --JobScheduler.totalQueueTime;
            --QueueTime;
        }
        
        if (heap[1] != null && heap[1].getDuration() <= 0){
           // If duration is 0, delete the head of queue.
           System.out.println("--------------------");
           System.out.println(String.format("Processor %d, Job %d, has finished executing at Time: %d second.",processorNum, heap[1].getJobNum(), JobScheduler.time));
           System.out.println(String.format("Had a total execution time of: %d seconds.", JobScheduler.time - heap[1].getArrivalTime()));
           System.out.println();
           if(queueSize <= 1){
               heap[idx] = null;
               queueSize = 0;
           } else {
               Job temp = heap[idx];
               heap[idx] = null;
               heap[1] = temp;
               idx=1;
               deleteOneFromQueueSize();
           }
    
            //Compare children and find which is larger
            while (2*idx <= queueSize){
                int leftChild = 2*idx;
                int rightChild = 2*idx+1;
                if (rightChild <= queueSize){ //If true there are 2 children nodes
                    if (heap[idx].getPriority() > heap[rightChild].getPriority() && heap[idx].getPriority() > heap[leftChild].getPriority()){
                        break; //Parent Node is largest
                    } else {
                        if(heap[rightChild].getPriority() > heap[leftChild].getPriority()){
                            //swap right child with parent
                            Job temp2 = heap[rightChild];
                            heap[rightChild] = heap[idx];
                            heap[idx] = temp2;
                            idx+=2;
                        } else {
                            //swap left child with parent
                            Job temp1 = heap[leftChild];
                            heap[leftChild] = heap[idx];
                            heap[idx] = temp1;
                            idx+=1;                            
                        }
                    }
                } else {
                    if(heap[idx].getPriority() > heap[leftChild].getPriority()){
                        break;
                    } else {
                            Job temp1 = heap[leftChild];
                            heap[leftChild] = heap[idx];
                            heap[idx] = temp1;
                            idx+=1;                          
                    }
                }
            }
            if(queueSize != 0 && heap[1] != null){
                System.out.println(String.format("Processor %d, Job %d, has started executing at Time: %d second.",processorNum, heap[1].getJobNum(), JobScheduler.time));
            }
        }

    }
    public void addOneToQueue(){     
        queueSize++;
    }
    public void deleteOneFromQueueSize(){
        queueSize--;
    }
    public int getQueueTime(){
        return QueueTime;
    }
    public void printQueue(){
        if (heap[1] != null){
            if(heap[1].getFlag() == false){
                System.out.println("--------------------");
                System.out.println(String.format("Processor %d",processorNum));
                System.out.println(String.format("  Overall Time: %d seconds",JobScheduler.time));
                System.out.println(String.format("  Job#: %d", heap[1].getJobNum()));
                System.out.println(String.format("  Initial Wait Time: %d", heap[1].getWhenStarted()- heap[1].getArrivalTime()));
                System.out.println(String.format("  Execution Time: %d/%d", (heap[1].getStartDuration() - heap[1].getDuration()),heap[1].getStartDuration()));               
            } else {
                System.out.println("--------------------");
                System.out.println(String.format("Processor %d",processorNum));
                System.out.println(String.format("  Overall Time: %d seconds",JobScheduler.time));
                System.out.println(String.format("  Job#: %d", heap[1].getJobNum()));
                System.out.println(String.format("  Initial Wait Time: Initializing..."));
                System.out.println(String.format("  Execution Time: %d/%d", (heap[1].getStartDuration() - heap[1].getDuration()),heap[1].getStartDuration()));                 
            }

        } else {
            if (JobScheduler.totalJobs == 0 && heap[1] == null){              
            } else {
                System.out.println("--------------------");
                System.out.println(String.format("Processor %d",processorNum));
                System.out.println(String.format("  Overall Time: %d seconds.",JobScheduler.time));
            }
        }
    }
}

