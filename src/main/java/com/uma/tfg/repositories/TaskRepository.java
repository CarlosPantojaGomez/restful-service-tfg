package com.uma.tfg.repositories;

import com.uma.tfg.entities.Project;
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    public List<Task> findByAssignedUsers(User user);
    
    public List<Task> findByProject(Project project);
    
    @Query( value = "select * from task task where task.creator_id = :userId or task.id in (SELECT taskAs.task_id FROM TASK_ASSIGNED taskAs where user_id = :userId)", nativeQuery = true)
	List<Task> getTasksForUser(@Param("userId") String userId);

}
