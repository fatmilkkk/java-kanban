package model;

import model.*;
import org.junit.jupiter.api.Test;
import service.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerTest {
    private final HistoryManager history = Managers.getDefaultHistory();

    @Test
    void shouldAddTasksToHistory() {
        Task task = new Task("Task", "Desc", Status.NEW);
        task.setId(1); // Явно задаём ID
        history.add(task);
        assertEquals(List.of(task), history.getHistory());
    }

    @Test
    void shouldNotDuplicateTasks() {
        Task task = new Task("Dupe", "Test", Status.NEW);
        history.add(task);
        history.add(task); // Повторное добавление
        assertEquals(1, history.getHistory().size(), "История не должна дублировать задачи");
    }

    @Test
    void shouldRemoveTaskFromHistory() {
        Task task1 = new Task("First", "1", Status.NEW);
        task1.setId(1);
        Task task2 = new Task("Second", "2", Status.NEW);
        task2.setId(2);

        history.add(task1);
        history.add(task2);
        history.remove(1); // Удаляем task1

        assertEquals(List.of(task2), history.getHistory());
    }

    @Test
    void shouldKeepOrderAfterRemoval() {
        Task task1 = new Task("First", "1", Status.NEW);
        task1.setId(1);
        Task task2 = new Task("Second", "2", Status.NEW);
        task2.setId(2);
        Task task3 = new Task("Third", "3", Status.NEW);
        task3.setId(3);

        history.add(task1);
        history.add(task2);
        history.add(task3);
        history.remove(2); // Удаляем task2

        assertEquals(List.of(task1, task3), history.getHistory());
    }
}
