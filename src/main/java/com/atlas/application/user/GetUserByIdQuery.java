package com.atlas.application.user;

import java.util.UUID;

/**
 * Query object representing intent to fetch a user by id.
 */
public record GetUserByIdQuery(UUID userId) {}
