package model;

import java.time.LocalDateTime;
import java.util.UUID;

import static exception.RatingException.isCorrectStarValue;

public class Rating extends BaseEntity {
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
        this(user, mediaEntry, stars, "");
    }

    //database constructor
    public Rating(UUID id, LocalDateTime createdAt, User user, MediaEntry mediaEntry, int stars, String comment, boolean confirmed) {
        super(id, createdAt);

        setUser(user);
        setMediaEntry(mediaEntry);
        setStars(stars);
        setComment(comment);
        setConfirmed(confirmed);
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
