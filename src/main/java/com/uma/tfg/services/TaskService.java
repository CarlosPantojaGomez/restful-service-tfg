package com.uma.tfg.services;

import com.uma.tfg.entities.Activity;
import com.uma.tfg.entities.Project;
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.TaskImage;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.ActivityRepository;
import com.uma.tfg.repositories.TaskImageRepository;
import com.uma.tfg.repositories.TaskRepository;
import com.uma.tfg.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;

import java.time.LocalDate;
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
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TaskImageRepository taskImageRepository;

    public Task createTask(Task task) {
		User creator = userRepository.findByNicknameAndFlagActive(task.getCreator().getNickname(), 1);
		task.setCreator(creator);
        return taskRepository.save(task);
    }

    public void updateAfterCreation(Task task) {
    	taskRepository.save(task);
    }
    
    public void updateTask(Task task) {
        Task old = taskRepository.findById(task.getId()).orElseThrow(() -> new UserNotFoundException(task.getId()));
        

        old.setAssignedUsers(task.getAssignedUsers());
        old.setComments(task.getComments());
        old.setCreationDate(task.getCreationDate());
        old.setDescription(task.getDescription());
        old.setNumHours(task.getNumHours());
        old.setPriority(task.getPriority());
        old.setState(task.getState());
        old.setName(task.getName());
        old.setEndDate(task.getEndDate());
        old.setStartDate(task.getStartDate());
        
        List<TaskImage> taskImages =  taskImageRepository.findByTask(old);
    	
        taskImages.forEach((image)->{
        	System.out.println(image.getId());
			try {
				taskImageRepository.delete(image);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
        
        Set<TaskImage> images = new HashSet<>();
    	
        task.getImages().forEach((image)->{
    		image.setTask(task);
        	TaskImage taskImage = taskImageRepository.save(image); 
        	images.add(taskImage);
    	});
        
        old.setImages(images);
        
        taskRepository.save(old);
    	
    	Activity act = new Activity();

		User creator = userRepository.findByNicknameAndFlagActive(task.getCreator().getNickname(), 1);
		
        act.setAction("modificado");
        act.setActivityDate(LocalDate.now());
        act.setTask(old);
        act.setCreator(creator);

        Set<User> users = new HashSet<>();
		if(old.getAssignedUsers() != null) {
			users.addAll(old.getAssignedUsers());
			
		}
		if(old.getProject().getUsersRelated() != null) {
			users.addAll(old.getProject().getUsersRelated());
		}
		
        act.setAssignedUsers(users);
        
        activityRepository.save(act);
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
        	
        	Activity act = new Activity();
            act.setAction("modificado");
            act.setActivityDate(LocalDate.now());
            act.setTask(old);
    		User creator = userRepository.findByNicknameAndFlagActive(task.getCreator().getNickname(), 1);
            act.setCreator(creator);

            Set<User> users = new HashSet<>();
    		if(old.getAssignedUsers() != null) {
    			users.addAll(old.getAssignedUsers());
    			
    		}
    		if(old.getProject().getUsersRelated() != null) {
    			users.addAll(old.getProject().getUsersRelated());
    		}
    		
            act.setAssignedUsers(users);
            
            activityRepository.save(act);
    	}
    }

    public void updateTaskUpdatePriorityState(Task task) {
        Task old = taskRepository.findById(task.getId()).orElseThrow(() -> new UserNotFoundException(task.getId()));
        
        old.setPriority(task.getPriority());
        old.setState(task.getState());
        

        taskRepository.save(old);
    	
    	Activity act = new Activity();
        act.setAction("modificado");
        act.setActivityDate(LocalDate.now());
        act.setTask(old);
		User creator = userRepository.findByNicknameAndFlagActive(task.getCreator().getNickname(), 1);
        act.setCreator(creator);

        Set<User> users = new HashSet<>();
		if(old.getAssignedUsers() != null) {
			users.addAll(old.getAssignedUsers());
			
		}
		if(old.getProject().getUsersRelated() != null) {
			users.addAll(old.getProject().getUsersRelated());
		}
		
        act.setAssignedUsers(users);
        
        activityRepository.save(act);
    }

    public List<Task> getAll(){
        return (List<Task>) taskRepository.findAllByFlagActive(1);
    }

    public Task getTask(Long id) throws Exception{
        return taskRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public List<Task> getTasksForUser(String userId) {
        return (List<Task>) taskRepository.getTasksForUser(userId);
    }
    
    public List<Task> getTasksAssignedUser(User user) {
        return (List<Task>) taskRepository.findByAssignedUsersAndFlagActive(user, 1);
    }
    
    public List<Task> getTasksByProject(Project project) {
        return (List<Task>) taskRepository.findByProjectAndFlagActive(project,1);
    }

    public void delete(Long id) throws Exception{
    	Optional<Task> task = taskRepository.findById(id);

    	task.get().setFlagActive(0);
    	
    	task.get().getActivities().forEach((activityRelated)->{
    		activityRepository.setFlagActive(0, activityRelated.getId());
    	});

    	task.get().setFlagActive(0);
    	
    	taskRepository.save(task.get());
    }
    
    public void addEmployeeToTask(Task task, Long id) throws Exception{
    	User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    	Set<User> users = task.getAssignedUsers();
    	users.add(user);
    	task.setAssignedUsers(users);
    	
        taskRepository.save(task);
    }

}
