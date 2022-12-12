package com.uma.tfg.repositories;

import com.uma.tfg.entities.Project;
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    public List<Task> findByAssignedUsersAndFlagActive(User user, Integer flag);
    
    public List<Task> findByProjectAndFlagActive(Project project, Integer flag);
    
    public List<Task> findAllByFlagActive(Integer flag);
    
    @Modifying
    @Query("update Task task set task.flagActive = :flagActive where task.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);
    
    @Query( value = "select * from task task where (task.creator_id = :userId or task.id in (SELECT taskAs.task_id FROM TASK_ASSIGNED taskAs where user_id = :userId)) and task.flag_active = 1", nativeQuery = true)
	List<Task> getTasksForUser(@Param("userId") String userId);

}
