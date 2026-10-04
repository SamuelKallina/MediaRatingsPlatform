package service;

import model.*;
import repository.RatingRepository;
import repository.UserRepository;

import java.util.Set;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            RatingRepository ratingRepository
    ) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public User register(String userName, String passwordHash, String nameTag) {

        if (userRepository.existsByUserName(userName)) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(userName, passwordHash, nameTag);

        return userRepository.save(user);
    }

    @Override
    public UserProfile getProfile(UUID userId) {

        User user = getUserById(userId);

        Set<Rating> ratings = getRatingHistory(userId);

        double averageRating = ratings.stream()
                .mapToInt(Rating::getStars)
                .average()
                .orElse(0);

        return new UserProfile(
                user.getUserName(),
                user.getNameTag(),
                ratings.size(),
                averageRating,
                getFavouriteGenre(userId)
        );
    }

    @Override
    public void editProfile(UUID userId, String nameTag) {

        User user = getUserById(userId);

        user.setNameTag(nameTag);

        userRepository.update(user);
    }

    @Override
    public Set<Rating> getRatingHistory(UUID userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public Set<MediaEntry> getFavourites(UUID userId) {

        User user = getUserById(userId);

        return user.getFavourites();
    }

    @Override
    public UserProfile getStatistics(UUID userId) {
        return getProfile(userId);
    }


    // Helper methods

    private User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }

    private Genre getFavouriteGenre(UUID userId) { // TODO
        return null;
    }
}