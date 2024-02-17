package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.List;

import Util.MoviesManagement;
import Util.SeriesManagement;
import Util.UserRegisteredManagement;
import co.edu.uptc.model.Admin;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.UserRegistered;

public class UserRegisteredController {

    MoviesManagement mm = new MoviesManagement();
    SeriesManagement sm = new SeriesManagement();
    private UserRegisteredManagement um = new UserRegisteredManagement();
    private ArrayList<UserRegistered> allUsers = um.getUsers();
    private UserRegistered currentUser;
    private Admin admin;
    
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Serie> series = new ArrayList<>();
    

    public ArrayList<UserRegistered> getAllUsers() {
        return allUsers;
    }

    public boolean idFound(int id){
        for(UserRegistered i: allUsers){
            if(i.getId() == id){
                currentUser = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserRegisteredController [allUsers=" + allUsers + ", currentUser=" + currentUser + "]";
    }

    public boolean userFound(String user){
        for(UserRegistered i: allUsers){
            if(i.getUser().equals(user)){currentUser = i;return true;}
        }
        return false;
    }

    

    public boolean addUser(String firstName, String lastName, int id, String password){
        UserRegistered r = new UserRegistered(firstName, lastName, id, firstName + id + "@uptc.edu.co", password);
        um.addUser(r);
        allUsers.add(r);
        return true;
    }

    public UserRegistered getCurrentuser(){return currentUser;}

    public boolean verifyPasswordAux(String password, String regex){
        boolean rta = false;
        char[] passwordChar = password.toCharArray();
        for(int i = 0; i < passwordChar.length && true; i++){
            if(String.valueOf(passwordChar[i]).matches(regex)){
                rta = true;
                break;
            }else if(i == passwordChar.length-1){return false;}
        }
        return rta;
    }


    public boolean validatePassword(String password) {
        boolean rta = false;
        //al menos un número, una mayúscula, una minúscula y un carácter especial.
        if(verifyPasswordAux(password, "[0-9]")){
            if(verifyPasswordAux(password, "[a-z]")){
                if(verifyPasswordAux(password, "[A-Z]")){
                    if(verifyPasswordAux(password, "[!@#$%^&*(),.?\":{}|<>]")){
                        if(!verifyPasswordAux(password, "(?=\\\\\\\\\\\\\\\\S+$)")){
                            if(password.matches(".{8,}$")){
                                rta = true;
                            }
                        }
                    }
                }
            }
        }
        return rta;
    }

    

    public void playMovie(int duracionMilisegundos, String movieName) {
        //Estefania//
        // Reproducir película//
        // String name = "";//
        // MultimediaContent mc = new MultimediaContent(name);//

        //controller classes dont use outputs. ¡To FIX!
        System.out.println("Reproduciendo la película " + movieName + "...");
        try {
            Thread.sleep(duracionMilisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Película Finalizada");
        
    }

    

    

    public void playSerie(int duracionMilisegundos, String serieName) {
        //Commentaried code from: Estefania//
        // Reproducir Serie//
        // int seasons = 0;//
        // Serie sp = new Serie(name, null);//
        System.out.println("Reproduciendo la serie " + serieName + "...");
        try {
            Thread.sleep(duracionMilisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Serie Finalizada");

    }

    // Buscar película
    public String[] getMovieNames() {
        String[] options = new String[movies.size()];
        for (int i = 0; i < movies.size(); i++) {
            options[i] = movies.get(i).getName() + "-" + movies.get(i).getId();
        }
        return options;
    }


    // Buscar serie
    public String[] getSerieNames() {
        String[] options = new String[series.size()];
        for (int i = 0; i < series.size(); i++) {
            options[i] = series.get(i).getName() + "-" +series.get(i).getId();
        }
        return options;
    }

    // Mostrar película
    public Movie getMovie(int movieId) {
        for (Movie movie : movies) {
            if (movie.getId() == movieId) {
                return movie;
            }
        }
        return null;
    }

    // Mostrar serie
    public Serie getSerie(int serieId) {
        for (Serie serie : series) {
            if (serie.getId() == serieId) {
                return serie;
            }
        }
        return null;
    }

    public String[] serieSeasonsNames(int serieId) {
        Serie serie = getSerie(serieId);
        if (serie != null) {
            List<Season> seasons = serie.getSeasons();
            String[] names = new String[seasons.size()];

            for (int i = 0; i < seasons.size(); i++) {
                names[i] = seasons.get(i).getSeasonName();
            }

            return names;
        } else {
            return null;
        }
    }

    public String[] serieChapterNames(int serieId, String seasonName) {
        Serie serie = getSerie(serieId);

        if (serie != null) {
            Season targetSeason = null;

            // Buscar el capítulo específica
            for (Season season : serie.getSeasons()) {
                if (season.getSeasonName().equals(seasonName)) {
                    targetSeason = season;
                    break;
                }
            }

            if (targetSeason != null) {
                List<MultimediaContent> chapters = targetSeason.getSeasonMultimediaContent();
                String[] names = new String[chapters.size()];

                for (int i = 0; i < chapters.size(); i++) {
                    names[i] = chapters.get(i).getName();
                }

                return names;
            }
        } else {
            return null;
        }

        return null;
    }
    
    public MultimediaContent getSerieChapter(int serieId, String seasonName, String chapterName) {
        Serie serie = getSerie(serieId);

        if (serie != null) {
            Season targetSeason = null;

            // Buscar la temporada específica
            for (Season season : serie.getSeasons()) {
                if (season.getSeasonName().equals(seasonName)) {
                    targetSeason = season;
                    break;
                }
            }

            if (targetSeason != null) {
                // Buscar el capítulo específico
                for (MultimediaContent chapter : targetSeason.getSeasonMultimediaContent()) {
                    if (chapter.getName().equals(chapterName)) {
                        return chapter;
                    }
                }

                return null;
            }
        }

        return null;
    }
    


   


    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public UserRegistered currentUser(String currentUser) {
        for(UserRegistered i: allUsers){
            if(i.getUser().equals(currentUser)){
                return i;
            }
        }
        return null;
    }
    

    public void setCurrentUser(int currentUser) {
        for(UserRegistered i: allUsers){
            if(i.getId() == currentUser){
                this.currentUser = i;
            }
        }
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }

    public UserRegistered getCurrentUser() {
        return currentUser;
    }

    

}