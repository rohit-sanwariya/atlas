package com.atlas.infrastructure.persistence.user;

import com.atlas.domain.user.UserStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_external_identity", columnNames = "external_identity")
        }
)
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "external_identity", nullable = false, updatable = false)
    private String externalIdentity;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected UserEntity() {
        // for JPA
    }

    public UserEntity(
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

    // getters only (no setters)

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
}
