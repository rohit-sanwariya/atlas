package com.atlas.application.user;

/**
 * Input message for the Create User use case.
 * Immutable, no behavior, no HTTP knowledge.
 */
public record CreateUserCommand(
        String externalIdentity,
        String displayName
) {}
