package sg.edu.ntu.taskflow_api.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ChatClient chatClient;

    public TaskController(TaskService taskService, ChatClient.Builder chatClientBuilder) {
        this.taskService = taskService;
        this.chatClient = chatClientBuilder.build();
    }

    // GET /api/tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST /api/tasks (201 Created)
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK); // or NO_CONTENT (204)
    }

    // PUT /api/tasks/{id}/complete
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable Long id) {
        Task completedTask = taskService.markTaskAsComplete(id);
        if (completedTask != null) {
            return new ResponseEntity<>(completedTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /api/tasks/summary
    @GetMapping("/summary")
    public ResponseEntity<String> getTaskSummary() {
        List<Task> tasks = taskService.findAllTasks();

        try {
           
            ObjectMapper objectMapper = new ObjectMapper();
            String tasksJson = objectMapper.writeValueAsString(tasks);

            String prompt = "Here is my current task list in JSON format: " + tasksJson +
                            ". Please give me a short, plain-English summary of what is pending and what is done.";

            String response = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating summary: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT /api/tasks/batch-complete
    @PutMapping("/batch-complete")
    public ResponseEntity<List<Task>> markMultipleAsComplete(@RequestBody List<Long> ids) {
        List<Task> updatedTasks = taskService.markTasksAsComplete(ids);
        return new ResponseEntity<>(updatedTasks, HttpStatus.OK);
    }

    // DELETE /api/tasks/batch-delete
    @DeleteMapping("/batch-delete")
    public ResponseEntity<Void> deleteMultipleTasks(@RequestBody List<Long> ids) {
        taskService.deleteTasks(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}