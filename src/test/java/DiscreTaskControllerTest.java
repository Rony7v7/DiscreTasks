import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Priority;
import com.discretask.model.Task;

public class DiscreTaskControllerTest {
    @Test
    void testAddUndo() {
        DiscretasksSystem system = new DiscretasksSystem();
        Calendar calendar = Calendar.getInstance();
        system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        assertEquals(1, system.getTasksByDeadLine().size());
        system.undo();
        assertEquals(0, system.getTasksByDeadLine().size());
    }

    @Test
    void testModifyUndo() {
        DiscretasksSystem system = new DiscretasksSystem();
        Calendar calendar = Calendar.getInstance();
        Task task = system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        system.editTask(task.getId(), "title2", "content2", Priority.LOW_PRIORITY, "userCategory2", calendar, false);
        assertEquals("title2", system.getTasksByDeadLine().poll().getTitle());
    }

    @Test
    void testDeleteUndo() {
        DiscretasksSystem system = new DiscretasksSystem();
        Calendar calendar = Calendar.getInstance();
        Task task = system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        system.deleteTask(task.getId(), false);
        assertEquals(0, system.getTasksByDeadLine().size());
        system.undo();
        assertEquals(1, system.getTasksByDeadLine().size());
    }

    @Test
    void add5TasksThenUndoAllOfThemAndITShouldBeEmpty() {
        DiscretasksSystem system = new DiscretasksSystem();
        Calendar calendar = Calendar.getInstance();
        system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);
        assertEquals(5, system.getTasksByDeadLine().size());
        system.undo();
        system.undo();
        system.undo();
        system.undo();
        system.undo();
        assertEquals(0, system.getTasksByDeadLine().size());
    }

    @Test
    void addingModifyingAndDeletingTwice() {
        DiscretasksSystem system = new DiscretasksSystem();
        Calendar calendar = Calendar.getInstance();
        Task task = system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);

        system.editTask(task.getId(), "title2", "content2", Priority.LOW_PRIORITY, "userCategory2", calendar, false);

        system.undo();
        system.undo();

        assertEquals(0, system.getTasksByDeadLine().size());
    }

    @Test
    void addingAndDeletingAndModifyinAndUndo() {
        DiscretasksSystem system = new DiscretasksSystem();
        Calendar calendar = Calendar.getInstance();
        Task task = system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);

        system.deleteTask(task.getId(), false);

        system.undo();

        system.editTask(task.getId(), "title2", "content2", Priority.LOW_PRIORITY, "userCategory2", calendar, false);

        system.undo();

        assertEquals("title", system.getTasksByDeadLine().poll().getTitle());
    }

    @Test
    void addingModiFyingDeletingAndUndo3Times() {
        DiscretasksSystem system = new DiscretasksSystem();
        Calendar calendar = Calendar.getInstance();
        Task task = system.addTask("title", "content", Priority.HIGH_PRIORITY, "userCategory", calendar);

        system.editTask(task.getId(), "title2", "content2", Priority.LOW_PRIORITY, "userCategory2", calendar, false);

        system.deleteTask(task.getId(), false);

        assertEquals(0, system.getTasksByDeadLine().size());

        system.undo();

        assertEquals(1, system.getTasksByDeadLine().size());

        system.undo();
        Task task2 = system.getTasksByDeadLine().poll();
        system.getTasksByDeadLine().add(task2);

        assertEquals("title", task2.getTitle());

        system.undo();
        assertEquals(0, system.getTasksByDeadLine().size());
    }
}
