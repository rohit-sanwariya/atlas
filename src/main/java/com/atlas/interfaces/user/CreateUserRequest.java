package com.atlas.interfaces.user;

import jakarta.validation.constraints.NotBlank;

/**
 * HTTP request payload for creating a user.
 * Validation here is input hygiene, not business rules.
 */
public record CreateUserRequest(
        @NotBlank String externalIdentity,
        @NotBlank String displayName
) {}
