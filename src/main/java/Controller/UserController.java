package Controller;

import Model.MediaEntry;
import Model.Rating;
import Model.UserProfile;

import java.util.Set;

public interface UserController {

    UserProfile getProfile();

    void editProfile(String nameTag);

    Set<Rating> getRatingHistory();

    Set<MediaEntry> getFavourites();

    UserProfile getStatistics();
}