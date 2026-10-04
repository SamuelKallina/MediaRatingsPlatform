package Controller;

import Model.MediaEntry;
import Model.Rating;
import Model.UserProfile;

import java.util.Set;

public class UserControllerImpl implements UserController {
    @Override
    public UserProfile getProfile() {
        return null;
    }

    @Override
    public void editProfile(String nameTag) {

    }

    @Override
    public Set<Rating> getRatingHistory() {
        return Set.of();
    }

    @Override
    public Set<MediaEntry> getFavourites() {
        return Set.of();
    }

    @Override
    public UserProfile getStatistics() {
        return null;
    }
}
