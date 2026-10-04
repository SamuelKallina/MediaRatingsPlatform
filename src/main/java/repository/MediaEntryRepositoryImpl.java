package repository;

import database.DatabaseConnection;
import model.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class MediaEntryRepositoryImpl implements MediaEntryRepository {

    private final UserRepository userRepository;

    public MediaEntryRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MediaEntry save(MediaEntry mediaEntry) {
        String sql = """
                INSERT INTO media_entries
                (
                    id,
                    title,
                    description,
                    media_type,
                    release_year,
                    age_restriction,
                    creator_id,
                    created_at,
                    avg_score,
                    fav_count
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, mediaEntry.getId());
            statement.setString(2, mediaEntry.getTitle());
            statement.setString(3, mediaEntry.getDescription());
            statement.setString(4, getMediaType(mediaEntry));
            statement.setInt(5, mediaEntry.getReleaseYear());
            statement.setInt(6, mediaEntry.getAgeRestr());
            statement.setObject(7, mediaEntry.getCreator().getId());
            statement.setTimestamp(
                    8,
                    Timestamp.valueOf(mediaEntry.getCreatedAt())
            );
            statement.setDouble(9, mediaEntry.getAvgScore());
            statement.setInt(10, mediaEntry.getFavCount());

            statement.executeUpdate();

            return mediaEntry;

        } catch (SQLException e) {
            throw new RuntimeException("Could not save media entry", e);
        }
    }

    @Override
    public MediaEntry update(MediaEntry mediaEntry) {
        String sql = """
                UPDATE media_entries
                SET title = ?,
                    description = ?,
                    release_year = ?,
                    age_restriction = ?,
                    avg_score = ?,
                    fav_count = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, mediaEntry.getTitle());
            statement.setString(2, mediaEntry.getDescription());
            statement.setInt(3, mediaEntry.getReleaseYear());
            statement.setInt(4, mediaEntry.getAgeRestr());
            statement.setDouble(5, mediaEntry.getAvgScore());
            statement.setInt(6, mediaEntry.getFavCount());
            statement.setObject(7, mediaEntry.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Media entry not found");
            }

            return mediaEntry;

        } catch (SQLException e) {
            throw new RuntimeException("Could not update media entry", e);
        }
    }

    @Override
    public Optional<MediaEntry> findById(UUID id) {
        String sql = """
                SELECT *
                FROM media_entries
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapMediaEntry(resultSet));
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Could not find media entry by id", e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        String sql = """
                DELETE FROM media_entries
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete media entry", e);
        }
    }

    private String getMediaType(MediaEntry mediaEntry) {
        if (mediaEntry instanceof Movie) {
            return "MOVIE";
        }

        if (mediaEntry instanceof Series) {
            return "SERIES";
        }

        if (mediaEntry instanceof Game) {
            return "GAME";
        }

        throw new RuntimeException("Unknown media type");
    }

    private MediaEntry mapMediaEntry(ResultSet resultSet) throws SQLException {

        UUID creatorId = resultSet.getObject("creator_id", UUID.class);

        User creator = userRepository.findById(creatorId)
                .orElseThrow(() ->
                        new RuntimeException("Creator not found")
                );

        String mediaType = resultSet.getString("media_type");

        UUID id = resultSet.getObject("id", UUID.class);
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        int releaseYear = resultSet.getInt("release_year");
        int ageRestriction = resultSet.getInt("age_restriction");

        Set<Genre> genres = new HashSet<>();

        return switch (mediaType) {

            case "MOVIE" -> new Movie(
                    id,
                    createdAt.toLocalDateTime(),
                    title,
                    description,
                    releaseYear,
                    genres,
                    ageRestriction,
                    creator,
                    resultSet.getInt("length_in_minutes")
            );

            case "SERIES" -> new Series(
                    id,
                    createdAt.toLocalDateTime(),
                    title,
                    description,
                    releaseYear,
                    genres,
                    ageRestriction,
                    creator,
                    resultSet.getInt("episodes")
            );

            case "GAME" -> new Game(
                    id,
                    createdAt.toLocalDateTime(),
                    title,
                    description,
                    releaseYear,
                    genres,
                    ageRestriction,
                    creator,
                    resultSet.getInt("play_time_in_hours")
            );

            default -> throw new RuntimeException(
                    "Unknown media type: " + mediaType
            );
        };
    }
}