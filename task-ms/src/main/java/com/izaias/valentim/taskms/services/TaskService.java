package com.izaias.valentim.taskms.services;

import com.izaias.valentim.taskms.feignclients.UserFeignClient;
import com.izaias.valentim.taskms.models.Task;
import com.izaias.valentim.taskms.models.feignEntities.UsernameResponseFeign;
import com.izaias.valentim.taskms.models.modelsToRequests.Usernames;
import com.izaias.valentim.taskms.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserFeignClient userFeignClient;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserFeignClient userFeignClient) {
        this.taskRepository = taskRepository;
        this.userFeignClient = userFeignClient;
    }

    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok().body(taskRepository.findAll());
    }

    public ResponseEntity<Task> getTaskById(Long id) {
        Task searchTask = taskRepository.findById(id).orElse(null);
        if (searchTask != null) {
            return ResponseEntity.ok().body(searchTask);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Task> createNewTask(Task taskToCreate) {
        return ResponseEntity.ok().body(taskRepository.save(taskToCreate));
    }

    private List<String> returnValidUsernames(Usernames usernamesToVerify) {
        try {
            Set<UsernameResponseFeign> usernamesResponse = userFeignClient.verifyListOfUsernames(usernamesToVerify).getBody();
            List<String> names = new ArrayList<>();
            assert usernamesResponse != null;
            usernamesResponse.forEach(username -> names.add(username.getUsername()));
            return names;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<Task> includeUsersToTask(Long id_task, Usernames listUsernames) {
        Task searchTask = taskRepository.findById(id_task).orElse(null);
        List<String> usernames = returnValidUsernames(listUsernames);
        if (searchTask != null && usernames != null) {
            usernames.forEach(searchTask::setNewTargetUsername);
            taskRepository.save(searchTask);
            return ResponseEntity.ok().body(searchTask);
        }
        return ResponseEntity.notFound().build();
    }


}
