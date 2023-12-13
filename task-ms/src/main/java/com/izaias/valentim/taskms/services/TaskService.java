package com.izaias.valentim.taskms.services;

import com.izaias.valentim.taskms.models.Task;
import com.izaias.valentim.taskms.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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

    public ResponseEntity<Task> createNewTask(Task taskToCreate){
        return ResponseEntity.ok().body(taskRepository.save(taskToCreate));
    }
    public ResponseEntity<Task> includeUsersToTask(Long id_task, List<String> usernames){
        Task searchTask = taskRepository.findById(id_task).orElse(null);
        if(searchTask != null){
            usernames.forEach(searchTask::setNewTargetUsername);
            taskRepository.save(searchTask);
            return ResponseEntity.ok().body(searchTask);
        }
        return ResponseEntity.notFound().build();
    }


}
