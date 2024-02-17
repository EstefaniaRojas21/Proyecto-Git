package Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class CategoryManagement {
    @SuppressWarnings("unchecked")
    public void addCategory(Category category) {

        JsonObject atributes = new JsonObject();

        atributes.put("name", category.getName());

        JsonArray movies = new JsonArray();
        JsonArray series = new JsonArray();

        atributes.put("movies", movies);
        atributes.put("series", series);

        JsonObject categoryObj = new JsonObject();
        categoryObj.put("category", atributes);

        // Reads if JSON exists
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\categories.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray categories = (JsonArray) currentJSON.get("categories");

            categories.add(categoryObj);

            currentJSON.put("categories", categories);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\categories.json")) {
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
    public void updateCategories(ArrayList<Category> categoriesArray) {
        JsonArray categories = new JsonArray();

        for (Category c : categoriesArray) {
            JsonObject atributes = new JsonObject();

            atributes.put("name", c.getName());

            JsonArray movies = new JsonArray();

            for (Movie movieAux : c.getMovies()) {
                JsonObject movieAtributes = new JsonObject();
                movieAtributes.put("id", (long) movieAux.getId());
                movieAtributes.put("duration", (long) movieAux.getDuration());
                movieAtributes.put("name", movieAux.getName());
                movieAtributes.put("description", movieAux.getDescription());
                movieAtributes.put("author", movieAux.getAuthor());

                JsonObject movieObj = new JsonObject();
                movieObj.put("movie", movieAtributes);
                movies.add(movieObj);
            }

            JsonArray series = new JsonArray();

            for (Serie serieAux : c.getSeries()) {
                JsonObject serieAtributes = new JsonObject();

                serieAtributes.put("id", serieAux.getId());
                serieAtributes.put("name", serieAux.getName());
                serieAtributes.put("author", serieAux.getAuthor());
                serieAtributes.put("description", serieAux.getDescription());

                JsonArray seasonsList = new JsonArray();

                for (Season i : serieAux.getSeasons()) {
                    JsonArray chapterList = new JsonArray();

                    JsonObject seasonAtributes = new JsonObject();
                    seasonAtributes.put("seasonName", i.getSeasonName());

                    for (MultimediaContent m : i.getSeasonMultimediaContent()) {
                        JsonObject chapterAtributes = new JsonObject();
                        chapterAtributes.put("duration", (long) m.getDuration());
                        chapterAtributes.put("name", m.getName());
                        chapterAtributes.put("description", m.getDescription());

                        JsonObject chapterObject = new JsonObject();
                        chapterObject.put("chapter", chapterAtributes);
                        chapterList.add(chapterObject);
                    }

                    seasonAtributes.put("chapters", chapterList);
                    JsonObject seasonObject = new JsonObject();
                    seasonObject.put("season", seasonAtributes);
                    seasonsList.add(seasonObject);
                }

                serieAtributes.put("seasons", seasonsList);
                JsonObject serieObj = new JsonObject();
                serieObj.put("serie", serieAtributes);
                series.add(serieObj);
            }

            atributes.put("movies", movies);
            atributes.put("series", series);

            JsonObject categoryObj = new JsonObject();
            categoryObj.put("category", atributes);
            categories.add(categoryObj);

        }

        // Reads if JSON exists
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\categories.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            currentJSON.put("categories", categories);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\categories.json")) {
                file.write(currentJSON.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> categoriesArray = new ArrayList<>();
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\categories.json")) {
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;

        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray categories = (JsonArray) JsonObject.get("categories");
        for (Object c : categories) {
            JsonObject category = (JsonObject) c;

            JsonObject catObj = (JsonObject) category.get("category");

            String name = (String) catObj.get("name");

            JsonArray movies = (JsonArray) catObj.get("movies");

            ArrayList<Movie> moviesArray = new ArrayList<>();

            // Movies
            for (Object movie : movies) {
                JsonObject movieObj = (JsonObject) movie;

                JsonObject movieObjAux = (JsonObject) movieObj.get("movie");

                long idMovie = (long) movieObjAux.get("id");
                long durationMovie = (long) movieObjAux.get("duration");
                String nameMovie = (String) movieObjAux.get("name");
                String authorMovie = (String) movieObjAux.get("author");
                String descriptionMovie = (String) movieObjAux.get("description");
                Movie m = new Movie((int) idMovie, nameMovie, authorMovie, descriptionMovie, (int) durationMovie);
                moviesArray.add(m);
            }

            ArrayList<Serie> seriesArray = new ArrayList<>();
            JsonArray series = (JsonArray) catObj.get("series");
            for (Object serie : series) {
                JsonObject serieObject = (JsonObject) serie;

                JsonObject serieObjectAux = (JsonObject) serieObject.get("serie");

                JsonArray seasonsList = (JsonArray) serieObjectAux.get("seasons");
                ArrayList<Season> seasons = new ArrayList<>();

                for (Object season : seasonsList) {
                    JsonObject seasonObject = (JsonObject) season;

                    JsonObject seasonObjectAux = (JsonObject) seasonObject.get("season");

                    JsonArray chaptersList = (JsonArray) seasonObjectAux.get("chapters");
                    ArrayList<MultimediaContent> chapters = new ArrayList<>();

                    for (Object chapter : chaptersList) {
                        JsonObject chapterObject = (JsonObject) chapter;

                        JsonObject chapterObjectAux = (JsonObject) chapterObject.get("chapter");

                        long durationChap = (long) chapterObjectAux.get("duration");
                        String nameChap = (String) chapterObjectAux.get("name");
                        String descriptionChap = (String) chapterObjectAux.get("description");

                        MultimediaContent newChap = new MultimediaContent((int) durationChap, nameChap,
                                descriptionChap);
                        chapters.add(newChap);
                    }

                    String nameSea = (String) seasonObjectAux.get("seasonName");

                    Season newSeason = new Season(nameSea, chapters);

                    seasons.add(newSeason);
                }

                long idSerie = (long) serieObjectAux.get("id");
                String nameSerie = (String) serieObjectAux.get("name");
                String authorSerie = (String) serieObjectAux.get("author");
                String descriptionSerie = (String) serieObjectAux.get("description");

                Serie newSerie = new Serie((int) idSerie, nameSerie, authorSerie, descriptionSerie, seasons);
                seriesArray.add(newSerie);

            }

            Category newCategory = new Category(name);
            newCategory.setMovies(moviesArray);
            newCategory.setSeries(seriesArray);
            categoriesArray.add(newCategory);

        }
        return categoriesArray;
    }
}
