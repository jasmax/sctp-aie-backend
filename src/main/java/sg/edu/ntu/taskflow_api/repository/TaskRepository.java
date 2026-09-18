package sg.edu.ntu.taskflow_api.repository;

import org.springframework.stereotype.Repository;
import sg.edu.ntu.taskflow_api.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private HashMap<Long, Task> taskMap = new HashMap<>();
    private Long idCounter = 1L;

    // Find all tasks
    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    // Find task by ID
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    // Save (Create or Update) task
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idCounter++);
        }
        taskMap.put(task.getId(), task);
        return task;
    }

    // Delete task
    public void delete(Long id) {
        taskMap.remove(id);
    }

    // Batch mark tasks as complete
    public List<Task> batchComplete(List<Long> ids) {
        List<Task> updatedTasks = new ArrayList<>();
        for (Long id : ids) {
            Task task = taskMap.get(id);
            if (task != null) {
                task.setCompleted(true);
                updatedTasks.add(task);
            }
        }
        return updatedTasks;
    }

    // Batch delete tasks
    public void batchDelete(List<Long> ids) {
        for (Long id : ids) {
            taskMap.remove(id);
        }
    }
}
