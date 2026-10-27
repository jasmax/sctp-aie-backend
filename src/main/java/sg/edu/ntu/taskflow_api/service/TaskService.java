package sg.edu.ntu.taskflow_api.service;

import org.springframework.stereotype.Service;
import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor Injection
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null); // or throw exception if preferred
    }

    public Task createTask(Task task) {
        // Apply defaults when the client omits optional fields
        if (task.getStatus() == null || task.getStatus().isBlank()) {
            task.setStatus("todo");
        }
        if (task.getPriority() == null || task.getPriority().isBlank()) {
            task.setPriority("low");
        }
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task existingTask = findTaskById(id);
        if (existingTask != null) {
            existingTask.setTitle(taskDetails.getTitle());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setStatus(taskDetails.getStatus());
            existingTask.setPriority(taskDetails.getPriority());
            return taskRepository.save(existingTask);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    /** Sets a task's status to "done" — matches the frontend's status-based model. */
    public Task markTaskAsComplete(Long id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus("done");
            return taskRepository.save(task);
        }
        return null;
    }
    public List<Task> markTasksAsComplete(List<Long> ids) {
        return taskRepository.batchComplete(ids);
    }

    public void deleteTasks(List<Long> ids) {
        taskRepository.batchDelete(ids);
    }
}