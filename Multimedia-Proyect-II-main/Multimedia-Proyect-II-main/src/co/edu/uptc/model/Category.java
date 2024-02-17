package co.edu.uptc.model;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Movie> movies;
    private ArrayList<Serie> series;

    
    public Category(String name) {

        this.name = name;
        movies = new ArrayList<>();
        series = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public void removeMovie(Movie movie){
        movies.remove(movie);
    }
    public ArrayList<Serie> getSeries() {
        return series;
    }
    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
    public void addSerie(Serie serie){
        series.add(serie);
    }
    public void removeSerie(Serie serie){
        series.remove(serie);
    }
    
    
    
}
