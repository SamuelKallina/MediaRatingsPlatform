package model;

import static exception.RatingException.isCorrectStarValue;

public class Rating {
    private User user; //is the owner
    private MediaEntry mediaEntry;
    private int stars;
    private String comment; //optional
    private boolean confirmed;

    public Rating(User user, MediaEntry mediaEntry, int stars, String comment) {
        super();

        setUser(user);
        setMediaEntry(mediaEntry);
        setStars(stars);
        setComment(comment);
        setConfirmed(false);
    }

    public Rating(User user, MediaEntry mediaEntry, int stars) {
        super();

        setUser(user);
        setMediaEntry(mediaEntry);
        setStars(stars);
        setComment("");
        setConfirmed(false);
    }




    //getter & setter



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MediaEntry getMediaEntry() {
        return mediaEntry;
    }

    public void setMediaEntry(MediaEntry mediaEntry) {
        this.mediaEntry = mediaEntry;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        isCorrectStarValue(stars);
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

}
