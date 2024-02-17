package co.edu.uptc.model;

import java.util.ArrayList;

public class UserRegistered extends Person{
    private ArrayList<PlayList> playList;
    private UserSubscription sub;
    
    
    public UserRegistered(){}
    public UserRegistered(String firstName, String lastName, int id, String user, String password) {
        super(firstName, lastName, id, user, password);
        playList = new ArrayList<>();
        
    }
    public ArrayList<PlayList> getplayList() {
        return playList;
    }
    public void setplayList(ArrayList<PlayList> playList) {
        this.playList = playList;
    }
    public void addplayList(PlayList playList) {
        this.playList.add(playList);
    }
    public void removeplayList(PlayList playList) {
        this.playList.remove(playList);
    }
    public UserSubscription getSub() {
        return sub;
    }
    public void setSub(UserSubscription sub) {
        this.sub = sub;
    }
    public boolean couldLogIn(String user, String password) {
        String[] userArray = user.split("@");
        if(userArray[0].equals(this.getFirstName() + this.getId())){
            if(userArray[1].equals("uptc.edu.co")){
                if(this.getUser().equals(user)){
                    return true;
                }
            }
        }
        return false;
    }
}