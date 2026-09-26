package Model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MediaEntry extends BaseEntity{
    private String title;
    private String description;
    private Set<Rating> ratings = new HashSet<Rating>();
    private String type;
    private LocalDateTime releaseYear;
    private Set<Genre> genres = new HashSet<Genre>();
    private int ageRestr;
    private int creatorId;
    private float avgScore;
    private int favCount;

    public MediaEntry() {
        super();
    }


    // Methods & logic
    public float calcAvgScore() { //TODO
        return 0;
    }

    public int countFavCount() { //TODO
        return favCount;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public LocalDateTime getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(LocalDateTime releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public int getAgeRestr() {
        return ageRestr;
    }
    public void setAgeRestr(int ageRestr) {
        this.ageRestr = ageRestr;
    }
    public int getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(int creatorId) { //TODO find out if needed
        this.creatorId = creatorId;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public int getFavCount() {
        return favCount;
    }


}
