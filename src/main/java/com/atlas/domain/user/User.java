package com.atlas.domain.user;


import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class User {

    private final UUID id;
    private final String externalIdentity;
    private final String displayName;
    private final UserStatus status;
    private final Instant createdAt;

    public User(
            UUID id,
            String externalIdentity,
            String displayName,
            UserStatus status,
            Instant createdAt
    ) {
        this.id = id;
        this.externalIdentity = externalIdentity;
        this.displayName = displayName;
        this.status = status;
        this.createdAt = createdAt;
    }

    // ---- Factory method (intent-based creation) ----
    public static User create(String externalIdentity, String displayName) {
        if (externalIdentity == null || externalIdentity.isBlank()) {
            throw new IllegalArgumentException("External identity must not be blank");
        }

        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("Display name must not be blank");
        }

        return new User(
                UUID.randomUUID(),
                externalIdentity.trim(),
                displayName.trim(),
                UserStatus.ACTIVE,
                Instant.now()
        );
    }

    // ---- Domain behavior ----
    public User disable() {
        if (this.status == UserStatus.DISABLED) {
            return this;
        }

        return new User(
                this.id,
                this.externalIdentity,
                this.displayName,
                UserStatus.DISABLED,
                this.createdAt
        );
    }

    // ---- Getters (no setters, ever) ----
    public UUID getId() {
        return id;
    }

    public String getExternalIdentity() {
        return externalIdentity;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // ---- Equality based on identity ----
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
