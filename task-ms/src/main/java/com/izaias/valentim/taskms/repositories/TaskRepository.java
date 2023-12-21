package com.izaias.valentim.taskms.repositories;

import com.izaias.valentim.taskms.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
