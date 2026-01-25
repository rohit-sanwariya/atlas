package com.atlas.interfaces.error;

import java.time.Instant;

public record ApiError(
        String message,
        int status,
        Instant timestamp
) {}
