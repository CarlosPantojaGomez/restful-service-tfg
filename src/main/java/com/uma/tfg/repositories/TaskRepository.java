package com.uma.tfg.repositories;

import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    public List<Task> findByAssignedUsers(User user);

}
