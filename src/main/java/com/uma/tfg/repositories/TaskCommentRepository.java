package com.uma.tfg.repositories;

import com.uma.tfg.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskCommentRepository extends CrudRepository<Task, Long> {
}
