package jobscheduler;
/**
 * @author Jacob
 */
public class Job {
    //Initialize values for class
    private int jobNum, priority, arrivalTime, duration, whenStarted, startDuration;
    private boolean flag = true;
    private boolean flagTwo = true;
    
    //Construct Objects here
    Job(int jobNum, int priority, int arrivalTime, int duration){
        this.jobNum = jobNum;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        startDuration = duration;
    }
    
    //Get object values here
    public int getJobNum(){
        return this.jobNum;
    }
    public int getPriority(){
        return this.priority;
    }
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    public int getDuration(){
        return this.duration;
    }
    public int getWhenStarted(){
        return whenStarted;
    }
    public int getStartDuration(){
        return startDuration;
    }
    public boolean getFlag(){
        return flag;
    }
    public boolean getFlagTwo(){
        return flagTwo;
    }
    //Set object values here
    public void setWhenStarted(int start){
        this.whenStarted = start;
    }
    public void setJobNum(int jobNum){
        this.jobNum = jobNum;
    }
    public void setPriority(int pri){
        this.priority = pri;
    }
    public void setArrivalTime(int arrTime){
        this.arrivalTime = arrTime;
    }
    public void subtractOneDuration(){
        this.duration = duration-1;
    }
    public void setFlag(){
        this.flag = false;
    }
    public void setFlagTwo(){
        this.flagTwo = false;
    }
}
