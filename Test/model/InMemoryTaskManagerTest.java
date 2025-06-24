package model;

import model.*;
import org.junit.jupiter.api.Test;
import service.*;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    @Test
    public void shouldAddAndFindTasksById() {
        TaskManager manager = Managers.getDefault();
        Task task = new Task("T1", "Desc", Status.NEW);
        manager.addTask(task);
        assertEquals(task, manager.getTask(task.getId()));
    }

    @Test
    public void shouldNotConflictManualAndAutoIds() {
        TaskManager manager = Managers.getDefault();
        Task task = new Task("Manual", "T", Status.NEW);
        manager.addTask(task);
        Task autoTask = new Task("Auto", "T", Status.NEW);
        manager.addTask(autoTask);
        assertNotEquals(task.getId(), autoTask.getId(), "ID не должны конфликтовать");
    }

    @Test
    public void taskShouldStayUnchangedAfterAdd() {
        TaskManager manager = Managers.getDefault();
        Task task = new Task("Keep", "Safe", Status.NEW);
        manager.addTask(task);
        Task fetched = manager.getTask(task.getId());
        assertEquals("Keep", fetched.getName());
        assertEquals("Safe", fetched.getDescription());
        assertEquals(Status.NEW, fetched.getStatus());
    }
}
