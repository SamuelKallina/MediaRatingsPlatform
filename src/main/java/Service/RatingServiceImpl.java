package Service;

import Model.MediaEntry;
import Model.Rating;
import Model.User;

import java.util.Objects;

import static Exception.MediaEntryException.notNull;
import static Exception.RatingException.isCorrectStarValue;

public class RatingServiceImpl implements RatingService { //TODO

    @Override
    public void rateMedia(User user, MediaEntry mediaEntry, int stars, String comment){
        notNull(mediaEntry, "Media Entry");
        notNull(user, "User");
        isCorrectStarValue(stars);
        mediaEntry.addRating(new Rating(user, mediaEntry, stars, Objects.requireNonNullElse(comment, "")));

    }

    @Override
    public void editRating(Rating rating, int stars, String comment) {

    }

    @Override
    public void deleteRating(Rating rating, User user) {

    }

    @Override
    public void likeRating(Rating rating, User user) { //only once per rating

    }

    @Override
    public void writeComment(Rating rating, String comment) {

    }


}
