package service;

import model.User;
import model.UserProfile;

import java.util.Set;
import java.util.UUID;
import model.MediaEntry;
import model.Rating;

public interface UserService {

    User register(String userName, String passwordHash, String nameTag);

    UserProfile getProfile(UUID userId);

    void editProfile(UUID userId, String nameTag);

    Set<Rating> getRatingHistory(UUID userId);

    Set<MediaEntry> getFavourites(UUID userId);

    UserProfile getStatistics(UUID userId);
}