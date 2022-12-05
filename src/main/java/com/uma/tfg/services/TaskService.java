package com.uma.tfg.services;

import com.uma.tfg.entities.Project;
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.TaskRepository;
import com.uma.tfg.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public void createTask(Task task) {
		User creator = userRepository.findByNicknameAndFlagActive(task.getCreator().getNickname(), 1);
		task.setCreator(creator);
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
        old.setName(task.getName());
        

        taskRepository.save(old);
    }
    
    public void updateTaskUsers(Task task) {
        Task old = taskRepository.findById(task.getId()).orElseThrow(() -> new UserNotFoundException(task.getId()));
        
        if(task.getAssignedUsers() != null) {

    		Set<User> usersRelated = new HashSet<>();
    		task.getAssignedUsers().forEach((user)->{
    			if(user.getId() != null) {
					Optional<User> userRelated;
					try {
						userRelated = userRepository.findById(user.getId());
						usersRelated.add(userRelated.get());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
        	});
    		
    		old.setAssignedUsers(usersRelated);
            taskRepository.save(old);
    	}
    }

    public void updateTaskUpdatePriorityState(Task task) {
        Task old = taskRepository.findById(task.getId()).orElseThrow(() -> new UserNotFoundException(task.getId()));
        
        old.setPriority(task.getPriority());
        old.setState(task.getState());
        

        taskRepository.save(old);
    }

    public List<Task> getAll(){
        return (List<Task>) taskRepository.findAll();
    }

    public Task getTask(Long id) throws Exception{
        return taskRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public List<Task> getTasksAssignedUser(User user) {
        return (List<Task>) taskRepository.findByAssignedUsers(user);
    }
    
    public List<Task> getTasksByProject(Project project) {
        return (List<Task>) taskRepository.findByProject(project);
    }

    public void delete(Long id) throws Exception{
        taskRepository.deleteById(id);
    }
    
    public void addEmployeeToTask(Task task, Long id) throws Exception{
    	User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    	Set<User> users = task.getAssignedUsers();
    	users.add(user);
    	task.setAssignedUsers(users);
    	
        taskRepository.save(task);
    }

}
