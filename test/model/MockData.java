package model;

public class MockData {
    private static int idCounter = 1;

    public static Task createTask(String name, String desc, Status status) {
        Task task = new Task(name, desc, status);
        task.setId(generateId());
        return task;
    }

    public static Epic createEpic(String name, String desc) {
        Epic epic = new Epic(name, desc);
        epic.setId(generateId());
        return epic;
    }

    public static Subtask createSubtask(String name, String desc, Status status, int epicId) {
        Subtask subtask = new Subtask(name, desc, status, epicId);
        subtask.setId(generateId());
        return subtask;
    }

    private static int generateId() {
        return idCounter++;
    }
}