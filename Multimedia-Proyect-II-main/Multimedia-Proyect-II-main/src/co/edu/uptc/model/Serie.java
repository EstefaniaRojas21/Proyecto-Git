package co.edu.uptc.model;


import java.util.ArrayList;

public class Serie extends MultimediaContent {
    private ArrayList<Season> seasons;

    public Serie(int id, String name, String author, String description, ArrayList<Season> seasons) {
        super(id, name, author, description);
        this.seasons = seasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public void removeSeasons(Season season) {
        this.seasons.remove(season);
    }

   

    @Override
    public String toString() {
        return "Id:" + getId() + "  name: " + getName() + "\nAuthor: " + getAuthor() + "\nDescription: "
                + getDescription();
    }

}
