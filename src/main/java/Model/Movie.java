package Model;

import java.util.Set;

import static Exception.MediaEntryException.movieLengthValid;

public class Movie extends MediaEntry {

    private int lengthInMinutes;

    public Movie(
            String title,
            String description,
            int releaseYear,
            Set<Genre> genres,
            int ageRestr,
            User creator,
            int lengthInMinutes
    ) {
        super(title, description, releaseYear, genres, ageRestr, creator);
        setLengthInMinutes(lengthInMinutes);
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        movieLengthValid(lengthInMinutes);

        this.lengthInMinutes = lengthInMinutes;
    }
}