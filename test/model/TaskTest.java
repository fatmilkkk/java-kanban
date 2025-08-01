package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void tasksAreEqualIfIdsMatch() {
        Task task1 = new Task("Test", "Test task", Status.NEW);
        Task task2 = new Task("Test", "Test task", Status.NEW);
        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны");
    }

    @Test
    public void subtasksAreEqualIfIdsMatch() {
        Subtask sub1 = new Subtask("Sub", "Subtask", Status.NEW, 10);
        Subtask sub2 = new Subtask("Sub", "Subtask", Status.NEW, 10);
        assertEquals(sub1, sub2, "Сабтаски с одинаковым ID должны быть равны");
    }

    @Test
    public void epicsAreEqualIfIdsMatch() {
        Epic epic1 = new Epic("Epic", "Desc");
        Epic epic2 = new Epic("Epic", "Desc");
        assertEquals(epic1, epic2, "Эпики с одинаковым ID должны быть равны");
    }
}
