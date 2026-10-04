package repository;

import model.MediaEntry;

import java.util.Optional;
import java.util.UUID;

public interface MediaEntryRepository {

    MediaEntry save(MediaEntry mediaEntry);

    MediaEntry update(MediaEntry mediaEntry);

    Optional<MediaEntry> findById(UUID id);

    void deleteById(UUID id);
}