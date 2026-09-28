package Exception;

import Model.Rating;

public class RatingException extends RuntimeException {
    public RatingException(String message) {
        super(message);
    }

    public static void isCorrectStarValue(int value) {
        if (value < 1 || value > 5) throw new RatingException("Stars given must be between 1 and 5");
    }

    public static void ratingNotNull(Rating value) {
        if (value == null)  throw new NullPointerException("Rating cannot be null");
    }
}
