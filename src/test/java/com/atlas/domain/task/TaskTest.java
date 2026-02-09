package com.atlas.domain.task;


import com.atlas.application.user.UserId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TaskTest {
    @Test
    void shouldCreateTaskWithValidOwnerAndTitle(){
        UserId ownerId = UserId.newId();
        Task task = Task.create(ownerId,"Write tests");

        assertNotNull(task.getId());
        assertEquals(ownerId, task.getUserId());
        assertEquals("Write tests", task.getTitle());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertNotNull(task.getCreatedAt());
    }

    @Test
    void shouldFailWhenOwnerIdIsNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Task.create(null, "Write tests"));
        assertEquals("Task must have a valid userId", exception.getMessage());
    }

    @Test
    void shouldFailToStartWhenNotInTodoStatus() {
        UserId ownerId = UserId.newId();
        Task task = Task.create(ownerId, "Write tests");
        Task inProgressTask = task.start();

        IllegalStateException exception = assertThrows(IllegalStateException.class, inProgressTask::start);
        assertEquals("Only tasks with TODO status can be started", exception.getMessage());
    }

    @Test
    void shouldFailWhenTitleIsNull(){
        UserId ownerId = UserId.newId();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Task.create(ownerId, null);
        });
        assertEquals("Task must have a valid title", exception.getMessage());
    }

    @Test
    void shouldFailToCompleteWhenNotInProgressStatus() {
        UserId ownerId = UserId.newId();
        Task task = Task.create(ownerId, "Write tests");

        IllegalStateException exception = assertThrows(IllegalStateException.class, task::complete);
        assertEquals("Only tasks with IN_PROGRESS status can be completed", exception.getMessage());
    }

    @Test
    void shouldCompleteTaskAfterStarting() {
        UserId ownerId = UserId.newId();
        Task task = Task.create(ownerId, "Write tests");
        Task inProgressTask = task.start();
        Task completedTask = inProgressTask.complete();

        assertEquals(TaskStatus.DONE, completedTask.getStatus());
        assertEquals(task.getId(), completedTask.getId());
        assertEquals(task.getUserId(), completedTask.getUserId());
        assertEquals(task.getTitle(), completedTask.getTitle());
        assertEquals(task.getCreatedAt(), completedTask.getCreatedAt());
    }
}
