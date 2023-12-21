package com.izaias.valentim.taskms.controllers;

import com.izaias.valentim.taskms.models.Task;
import com.izaias.valentim.taskms.models.modelsToRequests.Usernames;
import com.izaias.valentim.taskms.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Task> createNewTask(@RequestBody Task taskToCreate) {
        return taskService.createNewTask(taskToCreate);
    }
    @PostMapping(value = "{id_task}/include/users/")
    public ResponseEntity<Task> includeUsersOnTask(@PathVariable Long id_task, @RequestBody Usernames usernames){
        return taskService.includeUsersToTask(id_task, usernames.getUsernames());
    }

}
