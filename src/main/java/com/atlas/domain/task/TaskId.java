package com.atlas.domain.task;

import java.util.UUID;

public record TaskId(UUID id) {
    public static TaskId newId() {
        return new TaskId(UUID.randomUUID());
    }
}
