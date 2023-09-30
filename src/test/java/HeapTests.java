
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import com.discretask.model.Priority;
import com.discretask.model.Task;
import com.discretask.structures.Heap;

public class HeapTests {

    private Heap<Task> heap;

    void setUpScenary1() {
        heap = new Heap<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
    }

    void setUpScenary12() {
        heap = new Heap<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });
    }

    void setUpScenary2() {
        heap = new Heap<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
        Task task1 = new Task("Tarea1", "Contenido", Priority.NON_PRIORITY, "Categoria", Calendar.getInstance());
        Task task3 = new Task("Tarea3", "Contenido", Priority.NEUTRAL, "Categoria", Calendar.getInstance());
        Task task2 = new Task("Tarea2", "Contenido", Priority.PRIORITY, "Categoria", Calendar.getInstance());
        heap.add(task1);
        heap.add(task2);
        heap.add(task3);
    }

    void setUpScenary3() {
        heap = new Heap<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });
        // Create tasks with different deadlines
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        Calendar twoDaysLater = Calendar.getInstance();
        twoDaysLater.add(Calendar.DAY_OF_YEAR, 2);
        Calendar oneYearLater = Calendar.getInstance();
        oneYearLater.add(Calendar.YEAR, 1);

        Task task1 = new Task("Task1", "Content1", Priority.NON_PRIORITY, "Category1", tomorrow);
        Task task2 = new Task("Task2", "Content2", Priority.PRIORITY, "Category2", twoDaysLater);
        Task task3 = new Task("Task3", "Content3", Priority.NEUTRAL, "Category3", oneYearLater);
        Task task4 = new Task("Task4", "Content3", Priority.NEUTRAL, "Category3", now);

        // Add tasks to the heap
        heap.add(task1);
        heap.add(task2);
        heap.add(task3);
        heap.add(task4);
    }

    void setUpScenary4() {
        heap = new Heap<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });
        // Create tasks with different deadlines
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        Calendar twoDaysLater = Calendar.getInstance();
        twoDaysLater.add(Calendar.DAY_OF_YEAR, 2);
        Calendar oneYearLater = Calendar.getInstance();
        oneYearLater.add(Calendar.YEAR, 1);

        Task task1 = new Task("Task1", "Content1", Priority.NON_PRIORITY, "Category1", tomorrow);
        Task task2 = new Task("Task2", "Content2", Priority.PRIORITY, "Category2", twoDaysLater);
        Task task3 = new Task("Task3", "Content3", Priority.NEUTRAL, "Category3", oneYearLater);
        Task task4 = new Task("Task4", "Content3", Priority.NEUTRAL, "Category3", now);

        // Add tasks to the heap
        heap.add(task1);
        heap.add(task2);
        heap.add(task3);
        heap.add(task4);
    }

    void setUpScenary5() {
        heap = new Heap<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });
        // Create tasks with different deadlines
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        Calendar twoDaysLater = Calendar.getInstance();
        twoDaysLater.add(Calendar.DAY_OF_YEAR, 2);
        Calendar oneYearLater = Calendar.getInstance();
        oneYearLater.add(Calendar.YEAR, 1);

        Task task1 = new Task("Task1", "Content1", Priority.NON_PRIORITY, "Category1", tomorrow);
        Task task2 = new Task("Task2", "Content2", Priority.PRIORITY, "Category2", twoDaysLater);
        Task task3 = new Task("Task3", "Content3", Priority.NEUTRAL, "Category3", oneYearLater);
        Task task4 = new Task("Task4", "Content3", Priority.NEUTRAL, "Category3", now);

        // Add tasks to the heap
        heap.add(task1);
        heap.add(task2);
        heap.add(task3);
        heap.add(task4);
    }

    @Test
    public void canHandleTasksByPriority() {
        Heap<Task> heap = new Heap<Task>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
        Task task1 = new Task("Tarea1", "Contenido", Priority.NON_PRIORITY, "Categoria", Calendar.getInstance());
        Task task3 = new Task("Tarea3", "Contenido", Priority.NEUTRAL, "Categoria", Calendar.getInstance());
        Task task2 = new Task("Tarea2", "Contenido", Priority.PRIORITY, "Categoria", Calendar.getInstance());
        heap.add(task1);
        heap.add(task2);
        heap.add(task3);

        assertEquals(task2, heap.poll());
    }

    @Test
    public void canHandleTasksByDate() {
        Heap<Task> heap = new Heap<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });

        // Create tasks with different deadlines
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        Calendar twoDaysLater = Calendar.getInstance();
        twoDaysLater.add(Calendar.DAY_OF_YEAR, 2);
        Calendar oneYearLater = Calendar.getInstance();
        oneYearLater.add(Calendar.YEAR, 1);

        Task task1 = new Task("Task1", "Content1", Priority.NON_PRIORITY, "Category1", tomorrow);
        Task task2 = new Task("Task2", "Content2", Priority.PRIORITY, "Category2", twoDaysLater);
        Task task3 = new Task("Task3", "Content3", Priority.NEUTRAL, "Category3", oneYearLater);
        Task task4 = new Task("Task4", "Content3", Priority.NEUTRAL, "Category3", now);

        // Add tasks to the heap
        heap.add(task1);
        heap.add(task2);
        heap.add(task3);
        heap.add(task4);

        // Verify that tasks are retrieved in the correct order based on deadlines

        assertEquals(task4, heap.poll()); // Task1 should be the next due (tomorrow)
        assertEquals(task1, heap.poll()); // Task1 should be the next due (tomorrow)
        assertEquals(task2, heap.poll()); // Task2 should be the next due (2 days later)
        assertEquals(task3, heap.poll()); // Task3 should be the next due (1 year later)
    }

    // Test that a new PriorityQueue is empty
    @Test
    public void test_new_priority_queue_is_empty() {
        Heap<Integer> pq = new Heap<Integer>(Comparator.naturalOrder());
        assertTrue(pq.isEmpty());
    }

    // Test that the size of the PriorityQueue increases when an item is added
    @Test
    public void test_size_increases_when_item_added() {
        Heap<Integer> pq = new Heap<Integer>(Comparator.naturalOrder());
        pq.add(5);
        assertEquals(1, pq.size());
    }

    // Test that the PriorityQueue returns the smallest item when polled
    @Test
    public void test_poll_returns_smallest_item() {
        Heap<Integer> pq = new Heap<Integer>(Comparator.naturalOrder());
        pq.add(5);
        pq.add(3);
        pq.add(7);
        assertEquals(3, (int) pq.poll());
    }

    // Test that the PriorityQueue throws an exception when polled and empty
    @Test
    public void test_poll_throws_exception_when_empty() {
        Heap<Integer> pq = new Heap<Integer>(Comparator.naturalOrder());
        pq.poll();
    }

    // Test that the PriorityQueue can handle adding and polling null values
    @Test
    public void test_can_handle_null_values() {
        Heap<String> pq = new Heap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        assertThrows(NullPointerException.class, () -> pq.add(null));
    }

    // Test that the PriorityQueue can handle adding and polling duplicate values
    @Test
    public void test_can_handle_duplicate_values() {
        Heap<Integer> pq = new Heap<Integer>(Comparator.naturalOrder());
        pq.add(5);
        pq.add(5);
        assertEquals(5, (int) pq.poll());
        assertEquals(5, (int) pq.poll());
    }

    @Test
    public void test_heap_resize() {
        Heap<Integer> heap = new Heap<Integer>(Comparator.naturalOrder());
        for (int i = 0; i < 10; i++) {
            heap.add(i);
        }
        assertEquals(10, heap.size());
        heap.add(10);
        assertEquals(11, heap.size());
        assertEquals(0, heap.poll().intValue());
        assertEquals(1, heap.poll().intValue());
    }
}