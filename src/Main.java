import model.*;
import service.*;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

        System.out.println("Создаем задачи и эпики...");

        // 1. Создаем две задачи
        Task task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", Status.IN_PROGRESS);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        // 2. Создаем эпик с тремя подзадачами
        Epic epicWithSubtasks = new Epic("Эпик с подзадачами", "Описание эпика 1");
        taskManager.addEpic(epicWithSubtasks);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", Status.NEW, epicWithSubtasks.getId());
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", Status.IN_PROGRESS, epicWithSubtasks.getId());
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", Status.DONE, epicWithSubtasks.getId());

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);

        // 3. Создаем эпик без подзадач
        Epic epicWithoutSubtasks = new Epic("Пустой эпик", "Описание эпика 2");
        taskManager.addEpic(epicWithoutSubtasks);

        // 4. Запрашиваем задачи в разном порядке
        System.out.println("\nЗапрашиваем задачи в разном порядке...");

        // Первый запрос
        taskManager.getTask(task1.getId());
        taskManager.getEpic(epicWithSubtasks.getId());
        taskManager.getSubtask(subtask1.getId());
        printHistory(taskManager);

        // Второй запрос (частично повторяем)
        taskManager.getTask(task2.getId());
        taskManager.getTask(task1.getId()); // Повторный запрос
        taskManager.getSubtask(subtask2.getId());
        printHistory(taskManager);

        // Третий запрос
        taskManager.getEpic(epicWithoutSubtasks.getId());
        taskManager.getSubtask(subtask3.getId());
        taskManager.getTask(task2.getId());
        printHistory(taskManager);

        // 5. Удаляем задачу из истории
        System.out.println("\nУдаляем задачу 1...");
        taskManager.deleteTask(task1.getId());
        printHistory(taskManager);

        // 6. Удаляем эпик с подзадачами
        System.out.println("\nУдаляем эпик с подзадачами...");
        taskManager.deleteEpic(epicWithSubtasks.getId());
        printHistory(taskManager);
    }

    private static void printHistory(TaskManager manager) {
        System.out.println("\nТекущая история:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
        System.out.println("Количество задач в истории: " + manager.getHistory().size());
    }
}
