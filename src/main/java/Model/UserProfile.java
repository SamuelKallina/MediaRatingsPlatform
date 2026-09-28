package Model;

public record UserProfile(
        String userName,
        String nameTag,
        int totalRatings,
        double averageRating,
        Genre favouriteGenre
) {
}