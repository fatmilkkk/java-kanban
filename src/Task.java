import java.util.Objects;

public class Task {
    protected String name;
    protected String description;
    protected final int ID;
    protected Status status;

    public Task(String name, String description, int ID, Status status) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task other = (Task) obj;
        return ID == other.ID &&
                Objects.equals(name, other.name) &&
                Objects.equals(description, other.description) &&
                status == other.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ID, status);
    }
}