package service;

import model.MediaEntry;
import model.Rating;
import model.User;

public interface RatingService {

    void rateMedia(User user, MediaEntry mediaEntry, int stars, String comment);

    void editRating(Rating rating, int stars, String comment);

    void deleteRating(Rating rating, User user);

    void likeRating(Rating rating, User user);

    void writeComment(Rating rating, String comment);
}