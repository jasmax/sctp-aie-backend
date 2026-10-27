## TaskFlow API - Module 4 Deployment

### Live Application URL
https://sctp-aie-backend-production.up.railway.app/api/tasks

### Railway Dashboard
![Initial-Load](TestScreenshot/railway_dashboard.png)

### Live URL
![Initial-Load](TestScreenshot/live_url.png)

### Notes
- Built with Docker multi-stage build (Maven build stage + Alpine JRE runtime stage)
- OPENAI_API_KEY set as a Railway environment variable (not committed to code)


# TaskFlow API - Module 3 Assignment

**Task management API**

---

## Requirements

* Java: 21
* Framework: Spring Boot (Maven)
* Dependencies: Spring Web, Lombok, Spring Boot DevTools, Spring AI (OpenAI), OpenAI API key

---

## Project Structure
```text
src/main/java/sg/edu/ntu/taskflow_api/
├── controller/   # Endpoint
├── model/        # Task entity
├── repository/   # In-memory data and CRUD
└── service/      # business logic layer
```
* Endpoints: http://localhost:8080/api/tasks

---

## Postman Test Screenshots
### Create Task
![Initial-Load](TestScreenshot/CreateTask.png)
### Get All Tasks
![Initial-Load](TestScreenshot/GetAllTasks.png)
### Get Task By ID
![Initial-Load](TestScreenshot/GetTaskByID.png)
### UpdateTask By ID
![Initial-Load](TestScreenshot/UpdateTask.png)
### Mark As Complete By ID
![Initial-Load](TestScreenshot/MarkAsComplete.png)
### Tasks AI Summary
![Initial-Load](TestScreenshot/AISummary.png)
### Delete Task By ID
![Initial-Load](TestScreenshot/DeleteTask.png)
### Verify Delete Task By ID
![Initial-Load](TestScreenshot/VerifyDeleteTask.png)
## Mark as complete or Delete by Selection
### Tasks List
![Initial-Load](TestScreenshot/TasksList.png)
### Tasks Mark As Complete
![Initial-Load](TestScreenshot/TasksMarkAsComplete.png)
### Updated Tasks List
![Initial-Load](TestScreenshot/UpdatedTasksList.png)
### Tasks Delete
![Initial-Load](TestScreenshot/TasksDelete.png)
### Final Tasks List
![Initial-Load](TestScreenshot/NewTasksList.png)

