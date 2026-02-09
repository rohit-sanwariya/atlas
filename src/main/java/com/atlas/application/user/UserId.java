package com.atlas.application.user;

import java.util.UUID;

/**
 * Application-layer return type for CreateUser use case.
 * Makes the output explicit and avoids leaking primitives.
 */
public record UserId(UUID value) {
    public static UserId newId() {
        return new UserId(UUID.randomUUID());
    }
}
