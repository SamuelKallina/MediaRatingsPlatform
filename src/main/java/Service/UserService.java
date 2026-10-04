package Service;

import Model.MediaEntry;
import Model.Rating;
import Model.UserProfile;

import java.util.Set;

public interface UserService {

    UserProfile getProfile();

    void editProfile(String nameTag);

    Set<Rating> getRatingHistory();

    Set<MediaEntry> getFavourites();

    UserProfile getStatistics();
}