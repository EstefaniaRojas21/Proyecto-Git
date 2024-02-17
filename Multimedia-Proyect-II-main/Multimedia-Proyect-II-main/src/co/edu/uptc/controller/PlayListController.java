package co.edu.uptc.controller;


import java.util.ArrayList;

import Util.UserRegisteredManagement;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.UserRegistered;

public class PlayListController{
    private UserRegisteredManagement um = new UserRegisteredManagement();
    private UserRegisteredController userC = new UserRegisteredController();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Serie> series = new ArrayList<>();
    private PlayList currentPlayList;

    
    
    public int getSerieIndex(ArrayList<Serie> serie, int idSerie) {
        for(int i = 0; i < serie.size(); i++){
            if(serie.get(i).getId() == idSerie){
                return i;
            }
        }
        return -1;
    }

    public int getSeasonIndex(ArrayList<Season> season, String seasonName) {
        for(int i = 0; i < season.size(); i++){
            if(season.get(i).getSeasonName().equals(seasonName)){
                return i;
            }
        }
        return -1;
    }

    public String[] playListNames(){
        String playListNames[] = new String[userC.getCurrentUser().getplayList().size()];
        for(int i = 0; i < userC.getCurrentUser().getplayList().size(); i++){
            playListNames[i] = userC.getCurrentUser().getplayList().get(i).getName();
        }
        return playListNames;
    }

    public void playListFound(String name){
        for(PlayList i: userC.getCurrentUser().getplayList()){
            if(i.getName().equals(name)){
                currentPlayList = i;
            }
        }
    }

    public String[] playListMovieNames(){
        String names[] = new String[currentPlayList.getMovies().size()];
        for(int i = 0; i < currentPlayList.getMovies().size(); i++){
            names[i] = currentPlayList.getMovies().get(i).getName() + "-" + currentPlayList.getMovies().get(i).getId();
        }
        return names;
    }

    public String[] playListSerieNames(){
        String names[] = new String[currentPlayList.getSeries().size()];
        for(int i = 0; i < currentPlayList.getSeries().size(); i++){
            names[i] = currentPlayList.getSeries().get(i).getName() + "-" + currentPlayList.getSeries().get(i).getId();
        }
        return names;
    }

    public String[] playListSeasonsNames(int idSerie){
        int seriePosition = getSerieIndex(currentPlayList.getSeries(), idSerie);

        String names[] = new String[currentPlayList.getSeries().get(seriePosition).getSeasons().size()];
        for(int i = 0; i < currentPlayList.getSeries().get(seriePosition).getSeasons().size(); i++){
            names[i] = currentPlayList.getSeries().get(seriePosition).getSeasons().get(i).getSeasonName();
        }
        return names;
    }

    public String[] playListChapterNames(int idSerie, String seasonName){
        int seriePosition = getSerieIndex(currentPlayList.getSeries(), idSerie);
        int seasonPosition = getSeasonIndex(currentPlayList.getSeries().get(seriePosition).getSeasons(), seasonName);

        String names[] = new String[currentPlayList.getSeries().get(seriePosition).getSeasons().get(seasonPosition).getSeasonMultimediaContent().size()];
        for(int i = 0; i < currentPlayList.getSeries().get(seriePosition).getSeasons().get(seasonPosition).getSeasonMultimediaContent().size(); i++){
            names[i] = currentPlayList.getSeries().get(seriePosition).getSeasons().get(seasonPosition).getSeasonMultimediaContent().get(i).getName();
        }
        return names;
    }

    public MultimediaContent getPlayListChapter(int serieId, String seasonName, String chapterName){
        int seriePosition = getSerieIndex(currentPlayList.getSeries(), serieId);
        int seasonPosition = getSeasonIndex(currentPlayList.getSeries().get(seriePosition).getSeasons(), seasonName);

        for(int i = 0; i < currentPlayList.getSeries().get(seriePosition).getSeasons().get(seasonPosition).getSeasonMultimediaContent().size(); i++){
            if(currentPlayList.getSeries().get(seriePosition).getSeasons().get(seasonPosition).getSeasonMultimediaContent().get(i).getName().equals(chapterName)){
                return currentPlayList.getSeries().get(seriePosition).getSeasons().get(seasonPosition).getSeasonMultimediaContent().get(i);
            }
        }
        return null;
    }

    public String[] updateNames(String[] toUpdate, String toRemove){
        
        ArrayList<String> aux = new ArrayList<>();

        for(String i: toUpdate){aux.add(i);}

        aux.remove(toRemove);
        String[] newList = new String[aux.size()];
        for(int i = 0; i < aux.size(); i++){
            newList[i] = aux.get(i);
        }
        return newList;
    }

    public void addPlayList(String name){
        PlayList p = new PlayList(name);
        userC.getCurrentUser().addplayList(p);
        um.updateUsers(userC.getAllUsers());

    }

    public void addMovieToPlayList(String namePlayList, int idMovie){
        for(PlayList i: userC.getCurrentUser().getplayList()){
            if(i.getName().equals(namePlayList)){
                for(Movie j : movies){
                    if(j.getId() == idMovie){
                        i.addMovie(j);
                        break;
                    }
                }
                
            }
        }
        um.updateUsers(userC.getAllUsers());
    }

    public void addSerieToPlayList(String namePlayList, int idSerie){
        for(PlayList i: userC.getCurrentUser().getplayList()){
            if(i.getName().equals(namePlayList)){
                for(Serie j: series){
                    if(j.getId() == idSerie){
                        i.addSerie(j);
                        break;
                    }
                }
            }
        }
        um.updateUsers(userC.getAllUsers());
    }

    public void updateNamePlayList(String oldName, String newName){
        for(PlayList i: userC.getCurrentUser().getplayList()){
            if(i.getName().equals(oldName)){
                i.setName(newName);
            }
        }
    }

    public String[] moviesToAdd(){
        ArrayList<String> aux = new ArrayList<>();
        if(!currentPlayList.getMovies().isEmpty()){
            for(int i = 0; i < movies.size(); i++){
                for(int j = 0;j < currentPlayList.getMovies().size(); j++){
                    //If id is different
                    if(movies.get(i).getId() != currentPlayList.getMovies().get(j).getId() && j == currentPlayList.getMovies().size() - 1){
                        aux.add(movies.get(i).getName() + "-" + movies.get(i).getId());
                    }else if(movies.get(i).getId() == currentPlayList.getMovies().get(j).getId()){
                        break;
                    }
                }
            }
        }else{
            for(Movie i: movies){
                aux.add(i.getName() + "-" + i.getId());
            }
        }
        String[] moviesToAdd = new String[aux.size()];
        for(int i = 0; i < aux.size(); i++){
            moviesToAdd[i] = aux.get(i);
        }

        return moviesToAdd;
    }

    public String[] seriesToAdd(){
        ArrayList<String> aux = new ArrayList<>();
        if(!currentPlayList.getSeries().isEmpty()){
            for(int i = 0; i < series.size(); i++){
                for(int j = 0;j < currentPlayList.getSeries().size(); j++){
                    //If id is different
                    if(series.get(i).getId() != currentPlayList.getSeries().get(j).getId() && j == currentPlayList.getSeries().size() - 1){
                        aux.add(series.get(i).getName() + "-" + series.get(i).getId());
                    }else if(series.get(i).getId() == currentPlayList.getSeries().get(j).getId()){
                        break;
                    }
                }
            }
        }else{
            for(Serie i: series){
                aux.add(i.getName() + "-" + i.getId());
            }
        }

        String[] seriesToAdd = new String[aux.size()];
        for(int i = 0; i < aux.size(); i++){
            seriesToAdd[i] = aux.get(i);
        }

        return seriesToAdd;
    }

    public void removeMovie(String movie){
        String[] aux = movie.split("-");
        ArrayList<Movie> moviesNew = new ArrayList<>();
        for(Movie i: currentPlayList.getMovies()){
            if(!(i.getId() == Integer.parseInt(aux[1]))){
                moviesNew.add(i);
            }
        }
        currentPlayList.setMovies(moviesNew);
        um.updateUsers(userC.getAllUsers());
    }

    public void removeSerie(String serie){
        String[] aux = serie.split("-");
        ArrayList<Serie> seriesNew = new ArrayList<>();
        for(Serie i: currentPlayList.getSeries()){
            if(!(i.getId() == Integer.parseInt(aux[1]))){
                seriesNew.add(i);
            }
        }
        currentPlayList.setSeries(seriesNew);
        um.updateUsers(userC.getAllUsers());
    }

    public boolean nameRepeated(String namePLayList){
        for(PlayList i: userC.getCurrentUser().getplayList()){
            if(i.getName().equals(namePLayList)){
                return true;
            }
        }
        return false;
    }

    public void removePlayList(String name){
        for(PlayList i: userC.getCurrentUser().getplayList()){
            if(i.getName().equals(name)){
                userC.getCurrentUser().removeplayList(i);
                break;
            }
        }
        um.updateUsers(userC.getAllUsers());
    }

    public PlayList getCurrentPlayList() {
        return currentPlayList;
    }

    public void setCurrentPlayList(String currentPlayList) {
        for(PlayList i: userC.getCurrentUser().getplayList()){
            if(i.getName().equals(currentPlayList)){
                this.currentPlayList = i;
            }
        }
    }

    public UserRegistered getCurrentUser(){
        return userC.getCurrentUser();
    }

    public void setUserC(UserRegisteredController userC) {
        this.userC = userC;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
    


}