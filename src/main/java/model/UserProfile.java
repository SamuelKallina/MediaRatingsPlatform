package model;

public record UserProfile(
        String userName,
        String nameTag,
        int totalRatings,
        double averageRating,
        Genre favouriteGenre
) {
}