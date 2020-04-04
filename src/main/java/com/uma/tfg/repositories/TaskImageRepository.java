package com.uma.tfg.repositories;

import com.uma.tfg.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskImageRepository extends CrudRepository<Task, Long> {
}
