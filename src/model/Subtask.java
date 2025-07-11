package model;

public class Subtask extends Task {
    private final int epicID; // Для того чтобы знать к какому эпику относится сабтаск

    public Subtask(String name, String description, Status status, int epicID) {
        super(name, description, status);
        this.epicID = epicID;
    }

    public int getEpicId() {
        return epicID;
    }
}
