package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubtaskValidationTest {
    @Test
    public void subtaskCannotReferenceItselfAsEpic() {
        Subtask subtask = MockData.createSubtask("Sub", "Desc", Status.NEW, 10);

        assertNotEquals(subtask.getId(), subtask.getEpicId(),
                "Сабтаск не может ссылаться на самого себя как на эпик");
    }
}
