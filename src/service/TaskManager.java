package service;

import model.*;
import java.util.*;

public class TaskManager {
    private int idCounter = 0;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    public int generateId() {
        return ++idCounter;
    }

    // Таски
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    // Эпики
    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void updateEpic(Epic updatedEpic) {
        Epic existingEpic = epics.get(updatedEpic.getId());
        if (existingEpic != null) {
            existingEpic.setName(updatedEpic.getName()); // Меняется имя
            existingEpic.setDescription(updatedEpic.getDescription()); // Меняется описание
            // Подзадачи остаются не тронутыми
            updateEpicStatus(existingEpic);
        } else {
            System.out.println("Эпик с ID " + updatedEpic.getId() + " не найден.");
        }
    }

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (int subId : epic.getSubtaskIds()) {
                subtasks.remove(subId);
            }
        }
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void clearEpics() {
        for (Epic epic : epics.values()) {
            for (int subId : epic.getSubtaskIds()) {
                subtasks.remove(subId);
            }
        }
        epics.clear();
    }

    // Сабтаски
    public void addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            System.out.println("Ошибка: эпик с ID " + subtask.getEpicId() + " не найден. Подзадача не добавлена.");
            return;
        }

        subtasks.put(subtask.getId(), subtask);
        epic.getSubtaskIds().add(subtask.getId());
        updateEpicStatus(epic);
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            updateEpicStatus(epic);
        }
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.getSubtaskIds().remove((Integer) id);
                updateEpicStatus(epic);
            }
        }
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void clearSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateEpicStatus(epic);
        }
    }

    public List<Subtask> getSubtasksForEpic(int epicId) {
        List<Subtask> result = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if (epic != null) {
            for (int id : epic.getSubtaskIds()) {
                result.add(subtasks.get(id));
            }
        }
        return result;
    }

    private void updateEpicStatus(Epic epic) {
        List<Integer> subtaskIds = epic.getSubtaskIds();
        if (subtaskIds.isEmpty()) {
            epic.forceSetStatus(Status.NEW);
            return;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (int id : subtaskIds) {
            Status status = subtasks.get(id).getStatus();
            if (status != Status.NEW) {
                allNew = false;
            }
            if (status != Status.DONE) {
                allDone = false;
            }
        }

        if (allDone) {
            epic.forceSetStatus(Status.DONE);
        } else if (allNew) {
            epic.forceSetStatus(Status.NEW);
        } else {
            epic.forceSetStatus(Status.IN_PROGRESS);
        }
    }
}