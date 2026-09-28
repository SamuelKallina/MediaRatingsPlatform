package Model;

public class RatingLike extends BaseEntity {

    private User user;
    private Rating rating;

    public RatingLike(User user, Rating rating) {
        super();
        setUser(user);
        setRating(rating);
    }

    public User getUser() {
        return user;
    }

    public Rating getRating() {
        return rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}