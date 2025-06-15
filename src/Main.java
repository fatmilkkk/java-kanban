import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Простые задачи
        Task t1 = new Task("Купить продукты", "Сходить в магазин", manager.generateId(), Status.NEW);
        Task t2 = new Task("Помыть машину", "Вымыть снаружи и внутри", manager.generateId(), Status.IN_PROGRESS);
        manager.addTask(t1);
        manager.addTask(t2);

        // Эпик 1 с двумя подзадачами
        Epic e1 = new Epic("Организация свадьбы", "Праздник на 100 человек", manager.generateId());
        manager.addEpic(e1);

        Subtask s1 = new Subtask("Найти зал", "Найти банкетный зал", manager.generateId(), Status.DONE, e1.getId());
        Subtask s2 = new Subtask("Нанять фотографа", "Найти и договориться", manager.generateId(), Status.DONE, e1.getId());
        manager.addSubtask(s1);
        manager.addSubtask(s2);

        // Эпик 2 с одной подзадачей
        Epic e2 = new Epic("Переезд", "Переезд в новую квартиру", manager.generateId());
        manager.addEpic(e2);

        Subtask s3 = new Subtask("Упаковать вещи", "Сложить всё в коробки", manager.generateId(), Status.NEW, e2.getId());
        manager.addSubtask(s3);

        System.out.println("Все задачи: " + manager.getAllTasks());
        System.out.println("Все эпики: " + manager.getAllEpics());
        System.out.println("Все подзадачи: " + manager.getAllSubtasks());

        // Проверка обновления
        s3.setStatus(Status.DONE);
        manager.updateSubtask(s3);

        System.out.println("Статус эпика e2 после изменения статуса подзадачи: " + manager.getEpic(e2.getId()).getStatus());

        // Удаление
        manager.deleteTask(t1.getId());
        manager.deleteEpic(e1.getId());

        System.out.println("Оставшиеся задачи: " + manager.getAllTasks());
        System.out.println("Оставшиеся эпики: " + manager.getAllEpics());
    }
}
