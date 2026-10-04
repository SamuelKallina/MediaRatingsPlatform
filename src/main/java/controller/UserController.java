package controller;

import model.MediaEntry;
import model.Rating;
import model.UserProfile;

import java.util.Set;

public interface UserController {

    UserProfile getProfile();

    void editProfile(String nameTag);

    Set<Rating> getRatingHistory();

    Set<MediaEntry> getFavourites();

    UserProfile getStatistics();
}