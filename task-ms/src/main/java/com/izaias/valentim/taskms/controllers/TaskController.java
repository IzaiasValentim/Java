package com.izaias.valentim.taskms.controllers;

import com.izaias.valentim.taskms.feignclients.UserFeignClient;
import com.izaias.valentim.taskms.models.Task;
import com.izaias.valentim.taskms.models.feignEntities.UsernameResponseFeign;
import com.izaias.valentim.taskms.models.modelsToRequests.Usernames;
import com.izaias.valentim.taskms.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserFeignClient userFeignClient;


    @Autowired
    public TaskController(TaskService taskService, UserFeignClient userFeignClient) {
        this.taskService = taskService;
        this.userFeignClient = userFeignClient;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    @PostMapping("users/verify")
    public ResponseEntity<Set<UsernameResponseFeign>> verifyListOfUsernames(@RequestBody Usernames usernames) {
       return userFeignClient.verifyListOfUsernames(usernames);
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<Task> createNewTask(@RequestBody Task taskToCreate) {
        return taskService.createNewTask(taskToCreate);
    }
    @PostMapping(value = "{id_task}/include/users/")
    public ResponseEntity<Task> includeUsersOnTask(@PathVariable Long id_task, @RequestBody Usernames usernames){
        return taskService.includeUsersToTask(id_task, usernames);
    }

}
