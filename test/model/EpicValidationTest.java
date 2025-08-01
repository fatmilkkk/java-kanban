package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EpicValidationTest {

    @Test
    public void epicCannotBeItsOwnSubtask() {
        Epic epic = new Epic("Epic", "Desc");
        assertFalse(epic.getSubtaskIds().contains(epic.getId()),
                "Эпик не должен содержать свой ID как подзадачу");
    }
}
