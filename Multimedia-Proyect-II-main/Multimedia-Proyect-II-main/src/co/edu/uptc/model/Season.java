package co.edu.uptc.model;

import java.util.ArrayList;

public class Season {

    private String seasonName;
    private ArrayList<MultimediaContent> seasonMultimediaContent;

    public Season(String seasonName, ArrayList<MultimediaContent> seasonMultimediaContent) {

        this.seasonName = seasonName;
        this.seasonMultimediaContent = seasonMultimediaContent;
    }

    public ArrayList<MultimediaContent> getSeasonMultimediaContent() {
        return seasonMultimediaContent;
    }

    public void setSeasonMultimediaContent(ArrayList<MultimediaContent> seasonMultimediaContent) {
        this.seasonMultimediaContent = seasonMultimediaContent;
    }

    public void addSeasonMultimediaContent(MultimediaContent seasonMultimediaContent) {
        this.seasonMultimediaContent.add(seasonMultimediaContent);
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    @Override
    public String toString() {
        return "Season Name: " + seasonName;
    }

}
