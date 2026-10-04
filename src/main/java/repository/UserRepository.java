package repository;

import model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

    void deleteById(UUID id);
}