package Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class SeriesManagement {

    @SuppressWarnings("unchecked")
    public void addSerie(Serie serie) {

        JsonObject atributes = new JsonObject();

        atributes.put("id", serie.getId());
        atributes.put("name", serie.getName());
        atributes.put("author", serie.getAuthor());
        atributes.put("description", serie.getDescription());

        JsonArray seasonsList = new JsonArray();

        for (Season i : serie.getSeasons()) {
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

        atributes.put("seasons", seasonsList);

        JsonObject serieObject = new JsonObject();
        serieObject.put("serie", atributes);

        // Reads if JSON exists
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\series.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray seriesList = (JsonArray) currentJSON.get("series");

            seriesList.add(serieObject);

            currentJSON.put("series", seriesList);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\series.json")) {
                file.write(currentJSON.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Serie> getSeries() {
        ArrayList<Serie> seriesArray = new ArrayList<>();
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\series.json")) {
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;
        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray series = (JsonArray) JsonObject.get("series");
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

                    MultimediaContent newChap = new MultimediaContent((int) durationChap, nameChap, descriptionChap);
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
        return seriesArray;
    }

    @SuppressWarnings("unchecked")
    public void updateSerie(ArrayList<Serie> seriesArray) {
        JsonArray series = new JsonArray();

        for (Serie serieAux : seriesArray) {
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

        // Reads if JSON exists
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\series.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            currentJSON.put("series", series);

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\series.json")) {
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
    public void removeSerie(Serie serieToRemove) {

        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\series.json")) {
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray seriesList = (JsonArray) currentJSON.get("series");
            Iterator<Object> iterator = seriesList.iterator();

            while (iterator.hasNext()) {
                Object s = iterator.next();
                JsonObject sObj = (JsonObject) s;
                JsonObject sObjAux = (JsonObject) sObj.get("serie");

                if ((long) sObjAux.get("id") == (long) serieToRemove.getId()) {
                    iterator.remove();
                    break;
                }
            }

            try (FileWriter file = new FileWriter(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\administratorFile\\series.json")) {
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