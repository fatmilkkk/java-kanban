package service;

import model.Task;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> history = new ArrayList<>();
    private static final int MAX_HISTORY_SIZE = 10;

    @Override
    public void add(Task task) {
        if (task == null) return;

        history.add(task); // просто добавляем, даже если задача уже есть

        if (history.size() > MAX_HISTORY_SIZE) {
            history.removeFirst(); // удаляем самую старую если список заполнен
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return new ArrayList<>(history);
    }
}
