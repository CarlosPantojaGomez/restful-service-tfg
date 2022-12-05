package com.uma.tfg.repositories;

import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.TaskComment;
import com.uma.tfg.entities.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskCommentRepository extends CrudRepository<TaskComment, Long> {
	public List<TaskComment> findByTask(Task task);
	
	public List<TaskComment> findByCreator(User user);
}
