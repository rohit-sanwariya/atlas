package com.atlas.application.user;

import java.util.UUID;

/**
 * Explicit return type for the use case.
 * Avoids leaking domain or primitives.
 */
public record UserId(UUID value) {}
