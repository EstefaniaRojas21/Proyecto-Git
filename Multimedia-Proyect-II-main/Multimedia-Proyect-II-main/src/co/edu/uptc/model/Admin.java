package co.edu.uptc.model;

import java.util.ArrayList;

public class Admin extends Person{


    private ArrayList<Movie> movies;
    private ArrayList<Serie> series;
    
    public Admin(String firstName, String lastName, int id, String user, String password) {
        super(firstName, lastName, id, user, password);
        movies = new ArrayList<>();
        series = new ArrayList<>();
    }


    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }

    public boolean couldLogIn(String user, String password) {
        String[] userArray = user.split("@");
        if(userArray[0].equals(this.getFirstName() + this.getId())){
            if(userArray[1].equals("uptc.admin.co")){
                if(this.getUser().equals(user)){
                    return true;
                }
            }
        }
        return false;
    }
   
    

}
