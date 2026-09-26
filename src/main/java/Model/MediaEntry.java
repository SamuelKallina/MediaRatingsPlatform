package Model;

import java.time.LocalDateTime;
import java.util.Set;

public class MediaEntry extends BaseEntity{
    private String title;
    private String description;
    private Set<Rating> ratings;
    private String type;
    private LocalDateTime releaseYear;
    private Set<Genre> genres;
    private int ageRestr;
    private int creatorId;
    private long avgScore;
    private int favCount;


}
