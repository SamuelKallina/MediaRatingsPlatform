package model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static exception.MediaEntryException.movieLengthValid;

public class Movie extends MediaEntry {

    private int lengthInMinutes;

    public Movie(String title, String description, int releaseYear, Set<Genre> genres, int ageRestr, User creator, int lengthInMinutes) {
        super(title, description, releaseYear, genres, ageRestr, creator);
        setLengthInMinutes(lengthInMinutes);
    }

    public Movie(UUID id, LocalDateTime createdAt, String title, String description, int releaseYear,  Set<Genre> genres, int ageRestr,  User creator, int lengthInMinutes) {
        super(
                id,
                createdAt,
                title,
                description,
                releaseYear,
                genres,
                ageRestr,
                creator
        );

        setLengthInMinutes(lengthInMinutes);
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        movieLengthValid(lengthInMinutes);

        this.lengthInMinutes = lengthInMinutes;
    }

    public Movie(String title, String description, int releaseYear, Set<Genre> genres, int ageRestr, User creator) {
        super(title, description, releaseYear, genres, ageRestr, creator);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}