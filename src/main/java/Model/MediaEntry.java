package Model;

import java.util.HashSet;
import java.util.Set;

import static Exception.RatingException.ratingNotNull;

public abstract class MediaEntry extends BaseEntity {
    private String title;
    private String description;
    private Set<Rating> ratings = new HashSet<Rating>();
    private int releaseYear;
    private Set<Genre> genres = new HashSet<Genre>();
    private int ageRestr;
    private User creator;
    private double avgScore;
    private int favCount;

    public MediaEntry(
            String title,
            String description,
            int releaseYear,
            Set<Genre> genres,
            int ageRestr,
            User creator
    ) {
        super();

        setTitle(title);
        setDescription(description);
        setReleaseYear(releaseYear);
        setGenres(genres);
        setAgeRestr(ageRestr);
        setCreator(creator);
    }


    // Methods & logic
    public double calcAvgScore() {
        double sum = 0;
        int confirmedRatings = 0;

        for (Rating rating : ratings) {
            if (rating.isConfirmed()) {
                sum += rating.getStars();
                confirmedRatings++;
            }
        }

        if (confirmedRatings == 0) {
            avgScore = 0;
            return avgScore;
        }

        avgScore = sum / confirmedRatings;
        return avgScore;
    }

    public void addRating(Rating rating) {
        ratingNotNull(rating);
        ratings.add(rating);
        calcAvgScore();
    }

    public int countFavCount() { //TODO maybe in service
        return favCount;
    }

    public void removeRating(Rating rating) {
        ratingNotNull(rating);
        ratings.remove(rating);
        calcAvgScore();
    }

    public void addGenre(Genre genre) {
        if (genre != null) {
            genres.add(genre);
        }
    }

    public void removeGenre(Genre genre) {
        if (genre != null) {
            genres.remove(genre);
        }
    }


    //getter & setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        if (genres == null) {
            this.genres = new HashSet<>();
        } else {
            this.genres = genres;
        }
    }

    public int getAgeRestr() {
        return ageRestr;
    }

    public void setAgeRestr(int ageRestr) {
        this.ageRestr = ageRestr;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public int getFavCount() {
        return favCount;
    }


}
