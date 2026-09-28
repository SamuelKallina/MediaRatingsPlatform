package Model;

public class UserProfile {

    private String userName;
    private String nameTag;

    private int totalRatings;
    private double averageRating;
    private Genre favouriteGenre;

    public UserProfile(
            String userName,
            String nameTag,
            int totalRatings,
            double averageRating,
            Genre favouriteGenre
    ) {
        this.userName = userName;
        this.nameTag = nameTag;
        this.totalRatings = totalRatings;
        this.averageRating = averageRating;
        this.favouriteGenre = favouriteGenre;
    }

    public String getUserName() {
        return userName;
    }

    public String getNameTag() {
        return nameTag;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public Genre getFavouriteGenre() {
        return favouriteGenre;
    }
}