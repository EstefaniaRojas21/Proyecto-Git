package co.edu.uptc.controller;


import java.util.ArrayList;

import Util.CategoryManagement;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;


public class CategoryController{
    private CategoryManagement cm = new CategoryManagement();
    private UserRegisteredController userC = new UserRegisteredController();
    private ArrayList<Category> categories = cm.getCategories();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Serie> series = new ArrayList<>();
    private Category currentCategory;


    public String[] categoriesNames(){
        String categoriesNames[] = new String[categories.size()];
        for(int i = 0; i < categories.size(); i++){
            categoriesNames[i] = categories.get(i).getName();
        }
        return categoriesNames;
    }

     public void categoryFound(String name){
        for(Category i: categories){
            if(i.getName().equals(name)){
                currentCategory = i;
            }
        }
    }

    public String[] categoryMovieNames(){
        String names[] = new String[currentCategory.getMovies().size()];
        for(int i = 0; i < currentCategory.getMovies().size(); i++){
            names[i] = currentCategory.getMovies().get(i).getName() + "-" + currentCategory.getMovies().get(i).getId();
        }
        return names;
    }

    public String[] categorySerieNames(){
        String names[] = new String[currentCategory.getSeries().size()];
        for(int i = 0; i < currentCategory.getSeries().size(); i++){
            names[i] = currentCategory.getSeries().get(i).getName() + "-" + currentCategory.getSeries().get(i).getId();
        }
        return names;
    }

    public String[] moviesToAdd(){
        ArrayList<String> aux = new ArrayList<>();
        if(!currentCategory.getMovies().isEmpty()){
            for(int i = 0; i < movies.size(); i++){
                for(int j = 0;j < currentCategory.getMovies().size(); j++){
                    //If id is different
                    if(movies.get(i).getId() != currentCategory.getMovies().get(j).getId() && j == currentCategory.getMovies().size() - 1){
                        aux.add(movies.get(i).getName() + "-" + movies.get(i).getId());
                    }else if(movies.get(i).getId() == currentCategory.getMovies().get(j).getId()){
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
        if(!currentCategory.getSeries().isEmpty()){
            for(int i = 0; i < series.size(); i++){
                for(int j = 0;j < currentCategory.getSeries().size(); j++){
                    //If id is different
                    if(series.get(i).getId() != currentCategory.getSeries().get(j).getId() && j == currentCategory.getSeries().size() - 1){
                        aux.add(series.get(i).getName() + "-" + series.get(i).getId());
                    }else if(series.get(i).getId() == currentCategory.getSeries().get(j).getId()){
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

    public boolean nameRepeated(String name){
        for(Category i: categories){
            if(i.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void addCategory(String name){
        Category c = new Category(name);
        categories.add(c);
        cm.addCategory(c);
    }
    

    public void addMovieToCategory(String category, int idMovie){
        for(Category i: categories){
            if(i.getName().equals(category)){
                i.addMovie(userC.getMovie(idMovie));
                break;
            }
        }
        cm.updateCategories(categories);
    }

    public void addSerieToCategory(String category, int idSerie){
        for(Category i: categories){
            if(i.getName().equals(category)){
                i.addSerie(userC.getSerie(idSerie));
                break;
            }
        }
        cm.updateCategories(categories);
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

    public void updateNameCategory(String oldName, String newName){
        for(Category i: categories){
            if(i.getName().equals(oldName)){
                i.setName(newName);
            }
        }
    }

    public void removeMovie(String movie){
        String[] aux = movie.split("-");
        for(Movie i: currentCategory.getMovies()){
            if(i.getId() == Integer.parseInt(aux[1])){
                currentCategory.removeMovie(i);
                break;
            }
        }
        cm.updateCategories(categories);
    }

    public void removeSerie(String serie){
        String[] aux = serie.split("-");
        for(Serie i: currentCategory.getSeries()){
            if(i.getId() == Integer.parseInt(aux[1])){
                currentCategory.removeSerie(i);
                break;
            }
        }
        cm.updateCategories(categories);
    }

    public void removeCategory(String name){
        for(Category i: categories){
            if(i.getName().equals(name)){
                categories.remove(i);
                break;
            }
        }
        cm.updateCategories(categories);
    }

    
    public void setUserC(UserRegisteredController userC) {
        this.userC = userC;
    }
    public ArrayList<Category> getCategories() {
        return categories;
    }
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
    

}
