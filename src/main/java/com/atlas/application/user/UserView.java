package com.atlas.application.user;

import java.time.Instant;
import java.util.UUID;

/**
 * Read model for exposing user data.
 * This is NOT a domain object.
 */
public record UserView(
        UUID id,
        String externalIdentity,
        String displayName,
        String status,
        Instant createdAt
) {}
