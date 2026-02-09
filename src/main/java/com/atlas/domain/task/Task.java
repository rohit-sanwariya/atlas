package com.atlas.domain.task;

import com.atlas.application.user.UserId;

import java.time.Instant;
import java.util.Objects;

public final class Task {
    private final TaskId id;
    private final UserId userId;
    private final String title;
    private final TaskStatus status;
    private final Instant createdAt;
    private Task(TaskId id, UserId userId, String title, TaskStatus status, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Task create(UserId userId, String title) {
        if(userId == null) {
            throw  new IllegalArgumentException("Task must have a valid userId");
        }
        if(title == null) {
            throw  new IllegalArgumentException("Task must have a valid title");
        }
        return new Task(
                TaskId.newId(),
                userId,
                title.trim(),
                TaskStatus.TODO,
                Instant.now()
        );
    }

    public Task start(){
        if(status != TaskStatus.TODO){
            throw new IllegalStateException("Only tasks with TODO status can be started");
        }
        return new Task(
                this.id,
                this.userId,
                this.title,
                TaskStatus.IN_PROGRESS,
                this.createdAt
        );
    }

    public Task complete(){
        if(status != TaskStatus.IN_PROGRESS){
            throw new IllegalStateException("Only tasks with IN_PROGRESS status can be completed");
        }
        return new Task(
                this.id,
                this.userId,
                this.title,
                TaskStatus.DONE,
                this.createdAt
        );
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UserId getUserId() {
        return userId;
    }
    public String getTitle() {
        return title;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public TaskId getId() {
        return id;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
}
