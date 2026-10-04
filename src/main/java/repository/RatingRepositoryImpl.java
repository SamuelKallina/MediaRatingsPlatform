package repository;

import database.DatabaseConnection;
import model.MediaEntry;
import model.Rating;
import model.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class RatingRepositoryImpl implements RatingRepository {

    private final UserRepository userRepository;
    private final MediaEntryRepository mediaEntryRepository;

    public RatingRepositoryImpl(
            UserRepository userRepository,
            MediaEntryRepository mediaEntryRepository
    ) {
        this.userRepository = userRepository;
        this.mediaEntryRepository = mediaEntryRepository;
    }

    @Override
    public Rating save(Rating rating) {
        String sql = """
                INSERT INTO ratings
                (id, user_id, media_entry_id, stars, comment, confirmed, created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, rating.getId());
            statement.setObject(2, rating.getUser().getId());
            statement.setObject(3, rating.getMediaEntry().getId());
            statement.setInt(4, rating.getStars());
            statement.setString(5, rating.getComment());
            statement.setBoolean(6, rating.isConfirmed());
            statement.setTimestamp(
                    7,
                    Timestamp.valueOf(rating.getCreatedAt())
            );

            statement.executeUpdate();

            return rating;

        } catch (SQLException e) {
            throw new RuntimeException("Could not save rating", e);
        }
    }

    @Override
    public Rating update(Rating rating) {
        String sql = """
                UPDATE ratings
                SET stars = ?,
                    comment = ?,
                    confirmed = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, rating.getStars());
            statement.setString(2, rating.getComment());
            statement.setBoolean(3, rating.isConfirmed());
            statement.setObject(4, rating.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Rating not found");
            }

            return rating;

        } catch (SQLException e) {
            throw new RuntimeException("Could not update rating", e);
        }
    }

    @Override
    public Optional<Rating> findById(UUID id) {
        String sql = """
                SELECT *
                FROM ratings
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRating(resultSet));
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Could not find rating by id", e);
        }
    }

    @Override
    public Set<Rating> findByUserId(UUID userId) {
        String sql = """
                SELECT *
                FROM ratings
                WHERE user_id = ?
                """;

        Set<Rating> ratings = new HashSet<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ratings.add(mapRating(resultSet));
                }
            }

            return ratings;

        } catch (SQLException e) {
            throw new RuntimeException("Could not find ratings by user id", e);
        }
    }

    @Override
    public Set<Rating> findByMediaEntryId(UUID mediaEntryId) {
        String sql = """
                SELECT *
                FROM ratings
                WHERE media_entry_id = ?
                """;

        Set<Rating> ratings = new HashSet<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, mediaEntryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ratings.add(mapRating(resultSet));
                }
            }

            return ratings;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not find ratings by media entry id",
                    e
            );
        }
    }

    @Override
    public void deleteById(UUID id) {
        String sql = """
                DELETE FROM ratings
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete rating", e);
        }
    }

    private Rating mapRating(ResultSet resultSet) throws SQLException {
        UUID userId = resultSet.getObject("user_id", UUID.class);
        UUID mediaEntryId =
                resultSet.getObject("media_entry_id", UUID.class);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        MediaEntry mediaEntry =
                mediaEntryRepository.findById(mediaEntryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Media entry not found"
                                )
                        );

        return new Rating(
                resultSet.getObject("id", UUID.class),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                user,
                mediaEntry,
                resultSet.getInt("stars"),
                resultSet.getString("comment"),
                resultSet.getBoolean("confirmed")
        );
    }
}