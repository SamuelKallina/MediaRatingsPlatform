package repository;

import model.Rating;

import java.util.Set;
import java.util.UUID;

public interface RatingRepository {

    Set<Rating> findByUserId(UUID userId);
}