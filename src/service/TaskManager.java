package service;

import model.*;
import java.util.List;

public interface TaskManager {
    List<Task> getHistory();

    int generateId();

    // Таски
    void addTask(Task task);

    Task getTask(int id);

    void updateTask(Task task);

    void deleteTask(int id);

    List<Task> getAllTasks();

    void clearTasks();

    // Эпики
    void addEpic(Epic epic);

    Epic getEpic(int id);

    void updateEpic(Epic updatedEpic);

    void deleteEpic(int id);

    List<Epic> getAllEpics();

    void clearEpics();

    // Сабтаски
    void addSubtask(Subtask subtask);

    Subtask getSubtask(int id);

    void updateSubtask(Subtask subtask);

    void deleteSubtask(int id);

    List<Subtask> getAllSubtasks();

    void clearSubtasks();

    List<Subtask> getSubtasksForEpic(int epicId);
}
