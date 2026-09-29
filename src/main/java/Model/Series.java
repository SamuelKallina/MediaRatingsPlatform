package Model;

import java.util.Set;

import static Exception.MediaEntryException.seriesEpisodesValid;

public class Series extends MediaEntry {

    private int episodes;

    public Series(String title, String description, int releaseYear, Set<Genre> genres, int ageRestr, User creator, int episodes) {
        super(title, description, releaseYear, genres, ageRestr, creator);
        setEpisodes(episodes);
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        seriesEpisodesValid(episodes);
        this.episodes = episodes;
    }

    public Series(String title, String description, int releaseYear, Set<Genre> genres, int ageRestr, User creator) {
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