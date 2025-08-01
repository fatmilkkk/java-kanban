package model;

import org.junit.jupiter.api.Test;
import service.*;

import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {
    @Test
    public void managersShouldReturnWorkingInstances() {
        assertNotNull(Managers.getDefault(), "Менеджер задач должен быть инициализирован");
        assertNotNull(Managers.getDefaultHistory(), "Менеджер истории должен быть инициализирован");
    }
}
