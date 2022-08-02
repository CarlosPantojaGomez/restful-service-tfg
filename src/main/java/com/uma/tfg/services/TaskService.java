package com.uma.tfg.services;

import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        Task old = taskRepository.findById(task.getId()).orElseThrow(() -> new UserNotFoundException(task.getId()));

        old.setAssignedUsers(task.getAssignedUsers());
        old.setComments(task.getComments());
        old.setCreationDate(task.getCreationDate());
        old.setDescription(task.getDescription());
        old.setImages(task.getImages());
        old.setNumHours(task.getNumHours());
        old.setPriority(task.getPriority());
        old.setState(task.getState());
        

        taskRepository.save(old);
    }

    public List<Task> getAll(){
        return (List<Task>) taskRepository.findAll();
    }

    public List<Task> getTasksAssignedUser(User user) {
        return (List<Task>) taskRepository.findByAssignedUsers(user);
    }

    public void delete(Long id) throws Exception{
        taskRepository.deleteById(id);
    }

}
