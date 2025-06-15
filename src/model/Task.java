package model;

import java.util.Objects;

public class Task {
    protected String name;
    protected String description;
    protected final int id;
    protected Status status;

    public Task(String name, String description, int id, Status status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "ID=" + id +
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
        return id == other.id &&
                Objects.equals(name, other.name) &&
                Objects.equals(description, other.description) &&
                status == other.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }
}