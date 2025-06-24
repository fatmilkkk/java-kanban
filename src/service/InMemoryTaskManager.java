package service;

import model.*;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int idCounter = 0;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private final HistoryManager historyManager = new InMemoryHistoryManager();

    @Override
    public int generateId() {
        return ++idCounter;
    }

    // Таски
    @Override
    public void addTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        if (task != null) historyManager.add(task);
        return task;
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    // Эпики
    @Override
    public void addEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = epics.get(id);
        if (epic != null) historyManager.add(epic);
        return epic;
    }

    @Override
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

    @Override
    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (int subId : epic.getSubtaskIds()) {
                subtasks.remove(subId);
            }
        }
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void clearEpics() {
        for (Epic epic : epics.values()) {
            for (int subId : epic.getSubtaskIds()) {
                subtasks.remove(subId);
            }
        }
        epics.clear();
    }

    // Сабтаски
    @Override
    public void addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            System.out.println("Ошибка: эпик с ID " + subtask.getEpicId() + " не найден. Подзадача не добавлена.");
            return;
        }

        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        epic.getSubtaskIds().add(subtask.getId());
        updateEpicStatus(epic);
    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) historyManager.add(subtask);
        return subtask;
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            updateEpicStatus(epic);
        }
    }

    @Override
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

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void clearSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateEpicStatus(epic);
        }
    }

    @Override
    public ArrayList<Subtask> getSubtasksForEpic(int epicId) {
        ArrayList<Subtask> result = new ArrayList<>();
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

    @Override
    public ArrayList<Task> getHistory() {
        return new ArrayList<>(historyManager.getHistory());
    }
}