package co.edu.uptc.model;

public class UserSubscription extends Subscription{
    private long startTime;
    private long endTime;

    
    public UserSubscription(String name, int duration, String description, double price, long startTime, long endTime) {
        super(name, duration, description, price);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getEndTime() {
        return endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
}
