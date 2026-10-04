package repository;

import model.Rating;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RatingRepository {

    Rating save(Rating rating);

    Rating update(Rating rating);

    Optional<Rating> findById(UUID id);

    Set<Rating> findByUserId(UUID userId);

    Set<Rating> findByMediaEntryId(UUID mediaEntryId);

    void deleteById(UUID id);
}