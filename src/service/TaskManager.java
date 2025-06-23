package service;

import model.*;
import java.util.ArrayList;

public interface TaskManager {
    public ArrayList<Task> getHistory();

    public int generateId();

    // Таски
    public void addTask(Task task);

    public Task getTask(int id);

    public void updateTask(Task task);

    public void deleteTask(int id);

    public ArrayList<Task> getAllTasks();

    public void clearTasks();

    // Эпики
    public void addEpic(Epic epic);

    public Epic getEpic(int id);

    public void updateEpic(Epic updatedEpic);

    public void deleteEpic(int id);

    public ArrayList<Epic> getAllEpics();

    public void clearEpics();

    // Сабтаски
    public void addSubtask(Subtask subtask);

    public Subtask getSubtask(int id);

    public void updateSubtask(Subtask subtask);

    public void deleteSubtask(int id);

    public ArrayList<Subtask> getAllSubtasks();

    public void clearSubtasks();

    public ArrayList<Subtask> getSubtasksForEpic(int epicId);
}
