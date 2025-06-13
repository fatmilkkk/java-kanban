public class Subtask extends Task {
    private final int epicID; // Для того чтобы знать к какому эпику относится сабтаск

    public Subtask(String name, String description, int ID, Status status, int epicID) {
        super(name, description, ID, status);
        this.epicID = epicID;
    }

    public int getEpicID() {
        return epicID;
    }
}
