package Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import co.edu.uptc.model.Subscription;

public class SubscriptionsManagement {

    @SuppressWarnings("unchecked")
    public void addSubscription(Subscription sub) {

        JsonObject atributes = new JsonObject();

        atributes.put("name", sub.getName());
        atributes.put("description", sub.getDescription());
        atributes.put("price", (double) sub.getPrice());
        atributes.put("duration", sub.getDuration());

        JsonObject subObject = new JsonObject();
        subObject.put("subscription", atributes);

        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\subscriptions.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray subscriptionsList = (JsonArray) currentJSON.get("subscriptions");

            subscriptionsList.add(subObject);

            currentJSON.put("subscriptions", subscriptionsList);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\subscriptions.json")) {
                file.write(currentJSON.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Subscription> getSubscriptions() {
        ArrayList<Subscription> subsArray = new ArrayList<>();
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\subscriptions.json")) {
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;

        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray subs = (JsonArray) JsonObject.get("subscriptions");
        for (Object sub : subs) {
            JsonObject s = (JsonObject) sub;

            JsonObject so = (JsonObject) s.get("subscription");
            long duration = (long) so.get("duration");
            String name = (String) so.get("name");
            String description = (String) so.get("description");
            double price = (double) so.get("price");

            Subscription newSub = new Subscription(name, (int) duration, description, price);
            subsArray.add(newSub);
        }
        return subsArray;
    }

    @SuppressWarnings("unchecked")
    public void updateSubscription(Subscription subToUpdate, Subscription subUpdated) {
        JsonObject atributessubToUpdate = new JsonObject();

        atributessubToUpdate.put("duration", (long) subToUpdate.getDuration());
        atributessubToUpdate.put("name", subToUpdate.getName());
        atributessubToUpdate.put("description", subToUpdate.getDescription());
        atributessubToUpdate.put("price", (double) subToUpdate.getPrice());

        JsonObject subToUpdateObject = new JsonObject();
        subToUpdateObject.put("subscription", atributessubToUpdate);

        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\subscriptions.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray subsList = (JsonArray) currentJSON.get("subscriptions");
            subsList.remove(subToUpdateObject);

            JsonObject subUpdatedAtributes = new JsonObject();

            subUpdatedAtributes.put("price", (double) subUpdated.getPrice());
            subUpdatedAtributes.put("name", subUpdated.getName());
            subUpdatedAtributes.put("description", subUpdated.getDescription());
            subUpdatedAtributes.put("duration", subUpdated.getDuration());
            currentJSON.put("subscriptions", subsList);

            JsonObject subUpdatedObject = new JsonObject();
            subUpdatedObject.put("subscription", subUpdatedAtributes);

            subsList.add(subUpdatedObject);
            currentJSON.put("subscriptions", subsList);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\subscriptions.json")) {
                file.write(currentJSON.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void removeSubscription(Subscription subToRemove) {
        JsonObject atributessubToRemove = new JsonObject();

        atributessubToRemove.put("duration", (long) subToRemove.getDuration());
        atributessubToRemove.put("name", subToRemove.getName());
        atributessubToRemove.put("description", subToRemove.getDescription());
        atributessubToRemove.put("price", (double) subToRemove.getPrice());

        JsonObject subToRemoveObject = new JsonObject();
        subToRemoveObject.put("subscription", atributessubToRemove);

        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\subscriptions.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray subsList = (JsonArray) currentJSON.get("subscriptions");
            subsList.remove(subToRemoveObject);

            currentJSON.put("subscriptions", subsList);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\subscriptions.json")) {
                file.write(currentJSON.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
