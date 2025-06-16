package model;

import java.util.ArrayList;

public class Epic extends Task {
    // Чтобы эпик знал какие сабтаски к нему относятся
    private ArrayList<Integer> subtaskIds = new ArrayList<>();

    // Статус определяется автоматически
    public Epic(String name, String description, int ID) {
        super(name, description, ID, Status.NEW);
    }

    public ArrayList<Integer> getSubtaskIds() {
        return new ArrayList<>(subtaskIds);
    }

    // Ручная установка статуса у эпика запрещена
    @Override
    public void setStatus(Status status) {
        System.out.println("Нельзя вручную менять статус эпика.");
    }

    // Замена статуса происходит автоматически в service.TaskManager
    public void forceSetStatus(Status status) {
        this.status = status;
    }
}
