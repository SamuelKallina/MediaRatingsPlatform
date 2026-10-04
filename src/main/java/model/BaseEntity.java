package model;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntity {

    protected UUID id = UUID.randomUUID();
    protected LocalDateTime createdAt = LocalDateTime.now();

    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}