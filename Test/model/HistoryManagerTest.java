package model;

import model.*;
import org.junit.jupiter.api.Test;
import service.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerTest {
    @Test
    public void historyShouldKeepAddedTasks() {
        HistoryManager history = Managers.getDefaultHistory();
        Task task = new Task("T", "History", Status.NEW);
        history.add(task);
        List<Task> historyList = history.getHistory();
        assertEquals(1, historyList.size());
        assertEquals(task, historyList.get(0));
    }
}
