package co.edu.uptc.controller;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import Util.SubscriptionsManagement;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.model.UserSubscription;

public class SubscriptionController {
    private SubscriptionsManagement sm = new SubscriptionsManagement();
    private ArrayList<Subscription> subscriptions = sm.getSubscriptions();
    private Subscription currentSub;

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public String[] subsNames(){
        String[] subsNames = new String[subscriptions.size()];
        for(int i = 0; i < subscriptions.size(); i++){
            subsNames[i] = subscriptions.get(i).getName();
        }
        return subsNames;
    }

    public String[] subsAvailablesNames(UserSubscription userSub){
        ArrayList<String> aux = new ArrayList<>();

        if(userSub != null){
            for(Subscription s : subscriptions){
                if(!s.getName().equals(userSub.getName())){
                    aux.add(s.getName());
                }
            }
            String[] subsNames = new String[aux.size()];
            for(int i = 0; i < aux.size(); i++){
                subsNames[i] = aux.get(i);
            }
            return subsNames;
        }else{
            String[] subsNames = new String[subscriptions.size()];
            for(int i = 0; i < subscriptions.size(); i++){
                subsNames[i] = subscriptions.get(i).getName();
            }
            return subsNames;
        }
    }

    public void addSubToUser(Subscription sub, UserRegistered user){
        LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
        UserSubscription userSub = new UserSubscription(sub.getName(), sub.getDuration(), sub.getDescription(),
                         sub.getPrice(), currentLocalTime.toNanoOfDay() / 1_000_000, currentLocalTime.toNanoOfDay() / 1_000_000 + sub.getDuration());
        user.setSub(userSub);
    }

    public void cancelSub(UserRegistered user){
        user.setSub(null);
    }

    public void findCurrentSubscription(String nameSub){
        for(Subscription i: subscriptions){
            if(i.getName().equals(nameSub)){
                currentSub = i;
            }
        }
    }



    public void addSubscription(String name, String description, int duration, double price){
        Subscription newSub = new Subscription(name, duration, description, price);
        subscriptions.add(newSub);
        sm.addSubscription(newSub);
    }

    public void modifySubscription(Subscription subToUpdate, String newName, String newDescription, int newDuration, double newPrice){
        currentSub.setName(newName);
        currentSub.setDescription(newDescription);
        currentSub.setDuration(newDuration);
        currentSub.setPrice(newPrice);

        sm.updateSubscription(subToUpdate, currentSub);
    }

    public void removeSubscription(){
        sm.removeSubscription(currentSub);
        subscriptions.remove(currentSub);
    }

    public boolean nameFound(String name){
        for(Subscription s : subscriptions){
            if(s.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Subscription getCurrentSub() {
        return currentSub;
    }

    public boolean nameRepeated(String name){
        for(Subscription s: subscriptions){
            if(s.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean nameRepeatedForUpdate(String oldName, String newName){
        for(Subscription s: subscriptions){
            if(s.getName().equals(newName)){
                if(!(s.getName().equals(oldName))){
                    return true;
                }
            }
        }
        return false;
    }
    

    
}
