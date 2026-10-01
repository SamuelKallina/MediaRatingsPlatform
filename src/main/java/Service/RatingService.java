package Service;

import Model.MediaEntry;
import Model.Rating;
import Model.User;

import java.util.Objects;

import static Exception.MediaEntryException.notNull;
import static Exception.RatingException.isCorrectStarValue;

public class RatingService {

    public void rateMedia(User user, MediaEntry mediaEntry, int stars, String comment){
        notNull(mediaEntry, "Media Entry");
        notNull(user, "User");
        isCorrectStarValue(stars);
        mediaEntry.addRating(new Rating(user, mediaEntry, stars, Objects.requireNonNullElse(comment, "")));

    }

    public void editRating(){} //TODO

    public void deleteRating(){} //TODO

    public void likeRating(){} //TODO (only once per Rating

    public void writeComment(String comment){} //TODO

}
