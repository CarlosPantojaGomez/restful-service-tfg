package com.uma.tfg.repositories;

import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.TaskImage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskImageRepository extends CrudRepository<TaskImage, Long> {

	List<TaskImage> findByTask(Task task);
}
