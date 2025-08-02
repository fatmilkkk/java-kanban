package model;

import org.junit.jupiter.api.Test;
import service.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerTest {
    private final HistoryManager history = Managers.getDefaultHistory();

    @Test
    void shouldAddTasksToHistory() {
        Task task = MockData.createTask("Task", "Desc", Status.NEW);
        history.add(task);
        assertEquals(List.of(task), history.getHistory());
    }

    @Test
    void shouldNotDuplicateTasks() {
        Task task = MockData.createTask("Dupe", "Test", Status.NEW);
        history.add(task);
        history.add(task); // Повторное добавление
        assertEquals(1, history.getHistory().size(), "История не должна дублировать задачи");
    }

    @Test
    void shouldRemoveTaskFromHistory() {
        Task task1 = MockData.createTask("First", "1", Status.NEW);
        Task task2 = MockData.createTask("Second", "2", Status.NEW);

        history.add(task1);
        history.add(task2);
        history.remove(task1.getId()); // Удаляем task1

        assertEquals(List.of(task2), history.getHistory());
    }

    @Test
    void shouldKeepOrderAfterRemoval() {
        Task task1 = MockData.createTask("First", "1", Status.NEW);
        Task task2 = MockData.createTask("Second", "2", Status.NEW);
        Task task3 = MockData.createTask("Third", "3", Status.NEW);

        history.add(task1);
        history.add(task2);
        history.add(task3);
        history.remove(task2.getId()); // Удаляем task2

        assertEquals(List.of(task1, task3), history.getHistory());
    }
}
