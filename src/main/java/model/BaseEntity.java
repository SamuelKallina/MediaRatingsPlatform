package model;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntity {

    protected UUID id;
    protected LocalDateTime createdAt;


    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    // Database stuff
    public BaseEntity(UUID id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}