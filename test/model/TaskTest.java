package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void tasksAreEqualIfIdsMatch() {
        Task task1 = MockData.createTask("Test", "Test task", Status.NEW);
        Task task2 = MockData.createTask("Test", "Test task", Status.NEW);
        task2.setId(task1.getId());
        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны");
    }

    @Test
    public void subtasksAreEqualIfIdsMatch() {
        Subtask sub1 = MockData.createSubtask("Sub", "Subtask", Status.NEW, 10);
        Subtask sub2 = MockData.createSubtask("Sub", "Subtask", Status.NEW, 10);
        sub2.setId(sub1.getId());
        assertEquals(sub1, sub2, "Сабтаски с одинаковым ID должны быть равны");
    }

    @Test
    public void epicsAreEqualIfIdsMatch() {
        Epic epic1 = MockData.createEpic("Epic", "Desc");
        Epic epic2 = MockData.createEpic("Epic", "Desc");
        epic2.setId(epic1.getId());
        assertEquals(epic1, epic2, "Эпики с одинаковым ID должны быть равны");
    }
}
