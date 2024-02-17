package Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import co.edu.uptc.model.Movie;

public class MoviesManagement {

    // "Type safety: The method put(Object, Object) belongs to the raw type HashMap.
    // References to generic type HashMap<K,V> should be parameterizedJava"
    @SuppressWarnings("unchecked")
    public void addMovie(Movie movie) {

        JsonObject atributes = new JsonObject();

        atributes.put("id", movie.getId());
        atributes.put("name", movie.getName());
        atributes.put("author", movie.getAuthor());
        atributes.put("description", movie.getDescription());
        atributes.put("duration", movie.getDuration());

        JsonObject movieObject = new JsonObject();
        movieObject.put("movie", atributes);

        // Reads if JSON exists
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\movies.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray moviesList = (JsonArray) currentJSON.get("movies");

            moviesList.add(movieObject);

            currentJSON.put("movies", moviesList);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\movies.json")) {
                file.write(currentJSON.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> moviesArray = new ArrayList<>();
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\movies.json")) {
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;

        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray movies = (JsonArray) JsonObject.get("movies");
        for (Object movie : movies) {
            JsonObject m = (JsonObject) movie;

            JsonObject ma = (JsonObject) m.get("movie");
            long id = (long) ma.get("id");
            String name = (String) ma.get("name");
            String author = (String) ma.get("author");
            String description = (String) ma.get("description");
            long duration = (long) ma.get("duration");

            Movie mo = new Movie((int) id, name, author, description, (int) duration);
            moviesArray.add(mo);
        }
        return moviesArray;
    }

    @SuppressWarnings("unchecked")
    public void updateMovie(Movie movieToUpdate, Movie movieUpdated) {
        JsonObject atributesMovieToUpdate = new JsonObject();

        atributesMovieToUpdate.put("id", (long) movieToUpdate.getId());
        atributesMovieToUpdate.put("name", movieToUpdate.getName());
        atributesMovieToUpdate.put("author", movieToUpdate.getAuthor());
        atributesMovieToUpdate.put("description", movieToUpdate.getDescription());
        atributesMovieToUpdate.put("duration", (long) movieToUpdate.getDuration());

        JsonObject movieToUpdateObject = new JsonObject();
        movieToUpdateObject.put("movie", atributesMovieToUpdate);

        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\movies.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray moviesList = (JsonArray) currentJSON.get("movies");
            moviesList.remove(movieToUpdateObject);

            JsonObject movieUpdatedAtributes = new JsonObject();

            movieUpdatedAtributes.put("id", movieUpdated.getId());
            movieUpdatedAtributes.put("name", movieUpdated.getName());
            movieUpdatedAtributes.put("author", movieUpdated.getAuthor());
            movieUpdatedAtributes.put("description", movieUpdated.getDescription());
            movieUpdatedAtributes.put("duration", movieUpdated.getDuration());
            currentJSON.put("movies", moviesList);

            JsonObject movieUpdatedObject = new JsonObject();
            movieUpdatedObject.put("movie", movieUpdatedAtributes);

            moviesList.add(movieUpdatedObject);
            currentJSON.put("movies", moviesList);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\movies.json")) {
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
    public void removeMovie(Movie movieToRemove) {
        JsonObject atributesMovieToRemove = new JsonObject();

        atributesMovieToRemove.put("id", (long) movieToRemove.getId());
        atributesMovieToRemove.put("name", movieToRemove.getName());
        atributesMovieToRemove.put("author", movieToRemove.getAuthor());
        atributesMovieToRemove.put("description", movieToRemove.getDescription());
        atributesMovieToRemove.put("duration", (long) movieToRemove.getDuration());

        JsonObject movieToRemoveObject = new JsonObject();
        movieToRemoveObject.put("movie", atributesMovieToRemove);

        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\movies.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray moviesList = (JsonArray) currentJSON.get("movies");
            moviesList.remove(movieToRemoveObject);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\movies.json")) {
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
