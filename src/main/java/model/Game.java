package model;

import java.util.Set;

import static exception.MediaEntryException.gamePlayTimeValid;

public class Game extends MediaEntry {

    private int playTimeInHours;

    public Game(String title, String description, int releaseYear, Set<Genre> genres, int ageRestr, User creator, int playTimeInHours) {
        super(title, description, releaseYear, genres, ageRestr, creator);
        setPlayTimeInHours(playTimeInHours);
    }

    public int getPlayTimeInHours() {
        return playTimeInHours;
    }

    public void setPlayTimeInHours(int playTimeInHours) {
        gamePlayTimeValid(playTimeInHours);
        this.playTimeInHours = playTimeInHours;
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