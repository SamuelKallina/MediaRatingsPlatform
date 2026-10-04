package repository;

import model.User;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User save(User user) {// TODO PostgreSQL INSERT / UPDATE
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {// TODO SELECT
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserName(String userName) {// TODO SELECT
        return Optional.empty();
    }

    @Override
    public boolean existsByUserName(String userName) {// TODO SELECT EXISTS
        return false;
    }

    @Override
    public void deleteById(UUID id) {// TODO DELETE
    }
}