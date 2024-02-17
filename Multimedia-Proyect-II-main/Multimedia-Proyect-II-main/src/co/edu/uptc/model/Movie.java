package co.edu.uptc.model;

public class Movie extends MultimediaContent {

    public Movie(int id, String name, String author, String description, int duration) {
        super(id, name, duration, author, description);
    }

    @Override
    public String toString() {
        return "Name movie: " + getName() +
                "\nAuthor: " + getAuthor() +
                "\nDuration:  " + getDuration() +
                "\nDescription: " + getDescription();
    }

}
