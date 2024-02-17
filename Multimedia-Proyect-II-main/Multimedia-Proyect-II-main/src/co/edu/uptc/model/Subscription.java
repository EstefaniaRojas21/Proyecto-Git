package co.edu.uptc.model;

public class Subscription {
    private String name;
    private int duration;
    private String description;
    private double price;

    
    public Subscription(String name, int duration, String description, double price) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    
}