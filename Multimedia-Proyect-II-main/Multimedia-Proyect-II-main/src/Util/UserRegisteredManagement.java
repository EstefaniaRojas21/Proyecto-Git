package Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.model.UserSubscription;

public class UserRegisteredManagement {

    @SuppressWarnings("unchecked")
    public void addUser(UserRegistered user) {

        JsonObject atributes = new JsonObject();

        atributes.put("id", user.getId());
        atributes.put("firstName", user.getFirstName());
        atributes.put("lastName", user.getLastName());
        atributes.put("user", user.getUser());
        atributes.put("password", user.getPassword());
        atributes.put("subscription", user.getSub());

        JsonArray playLists = new JsonArray();

        atributes.put("playLists", playLists);

        JsonObject userObject = new JsonObject();
        userObject.put("user", atributes);

        // Reads if JSON exists
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray userList = (JsonArray) currentJSON.get("users");

            userList.add(userObject);

            currentJSON.put("users", userList);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
                file.write(currentJSON.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserRegistered> getUsers() {
        ArrayList<UserRegistered> userArray = new ArrayList<>();
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;

        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray users = (JsonArray) JsonObject.get("users");
        for (Object user : users) {
            JsonObject u = (JsonObject) user;

            JsonObject uo = (JsonObject) u.get("user");
            long id = (long) uo.get("id");
            String firstName = (String) uo.get("firstName");
            String lastName = (String) uo.get("lastName");
            String userString = (String) uo.get("user");
            String password = (String) uo.get("password");

            JsonArray playLists = (JsonArray) uo.get("playLists");
            // PLayList--Sumn coulda went wrong
            ArrayList<PlayList> playListArray = new ArrayList<>();
            for (Object playList : playLists) {
                JsonObject playListObj = (JsonObject) playList;

                JsonObject playListObjAux = (JsonObject) playListObj.get("playList");

                JsonArray movies = (JsonArray) playListObjAux.get("movies");
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

                // Series--Sum coulda went wrong
                ArrayList<Serie> seriesArray = new ArrayList<>();
                JsonArray series = (JsonArray) playListObjAux.get("series");
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

                PlayList p = new PlayList((String) playListObjAux.get("name"));
                p.setMovies(moviesArray);
                p.setSeries(seriesArray);
                playListArray.add(p);
            }

            UserRegistered ur = new UserRegistered(firstName, lastName, (int) id, userString, password);
            JsonObject sub = (JsonObject) uo.get("subscription");
            if (sub != null) {
                long durationSub = (long) sub.get("duration");
                String nameSub = (String) sub.get("name");
                String descriptionSub = (String) sub.get("description");
                double priceSub = (double) sub.get("price");
                long startTimeSub = (long) sub.get("startTime");
                long endTimeSub = (long) sub.get("endTime");

                UserSubscription userSub = new UserSubscription(nameSub, (int) durationSub, descriptionSub, priceSub,
                        startTimeSub, endTimeSub);
                ur.setSub(userSub);
            }

            ur.setplayList(playListArray);
            userArray.add(ur);
        }
        return userArray;
    }

    @SuppressWarnings("unchecked")
    public void updateUsers(ArrayList<UserRegistered> allusers) {
        JsonArray users = new JsonArray();

        for (UserRegistered user : allusers) {
            JsonObject atributes = new JsonObject();

            atributes.put("id", user.getId());
            atributes.put("firstName", user.getFirstName());
            atributes.put("lastName", user.getLastName());
            atributes.put("user", user.getUser());
            atributes.put("password", user.getPassword());

            JsonObject subAtributes = new JsonObject();
            if (user.getSub() != null) {
                subAtributes.put("name", user.getSub().getName());
                subAtributes.put("description", user.getSub().getDescription());
                subAtributes.put("duration", (long) user.getSub().getDuration());
                subAtributes.put("price", (double) user.getSub().getPrice());
                subAtributes.put("startTime", (long) user.getSub().getStartTime());
                subAtributes.put("endTime", (long) user.getSub().getEndTime());

                atributes.put("subscription", subAtributes);
            } else {
                atributes.put("subscription", null);
            }

            JsonArray playLists = new JsonArray();

            for (PlayList playList : user.getplayList()) {
                JsonObject playListsAtributes = new JsonObject();
                JsonArray movies = new JsonArray();

                for (Movie movieAux : playList.getMovies()) {
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

                for (Serie serieAux : playList.getSeries()) {
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

                playListsAtributes.put("movies", movies);
                playListsAtributes.put("series", series);
                playListsAtributes.put("name", playList.getName());

                JsonObject playListObj = new JsonObject();
                playListObj.put("playList", playListsAtributes);
                playLists.add(playListObj);
            }
            atributes.put("playLists", playLists);

            JsonObject userObj = new JsonObject();
            userObj.put("user", atributes);
            users.add(userObj);
        }

        // Reads if JSON exists
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            currentJSON.put("users", users);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
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