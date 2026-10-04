package repository;

import database.DatabaseConnection;
import model.User;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User save(User user) {
        String sql = """
                INSERT INTO users (id, username, password_hash, name_tag, created_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getNameTag());
            statement.setTimestamp(
                    5,
                    Timestamp.valueOf(user.getCreatedAt())
            );

            statement.executeUpdate();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Could not save user", e);
        }
    }

    @Override
    public User update(User user) {
        String sql = """
            UPDATE users
            SET username = ?,
                password_hash = ?,
                name_tag = ?
            WHERE id = ?
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getNameTag());
            statement.setObject(4, user.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("User not found");
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Could not update user", e);
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        String sql = """
                SELECT *
                FROM users
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapUser(resultSet));
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Could not find user by id", e);
        }
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        String sql = """
                SELECT *
                FROM users
                WHERE username = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, userName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapUser(resultSet));
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Could not find user by username", e);
        }
    }

    @Override
    public boolean existsByUserName(String userName) {
        String sql = """
                SELECT EXISTS(
                    SELECT 1
                    FROM users
                    WHERE username = ?
                )
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, userName);

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not check username", e);
        }
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("id", UUID.class),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getString("username"),
                resultSet.getString("password_hash"),
                resultSet.getString("name_tag")
        );
    }
}