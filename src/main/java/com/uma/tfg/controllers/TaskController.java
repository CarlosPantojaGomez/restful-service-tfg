package com.uma.tfg.controllers;

import com.uma.tfg.entities.Activity;
import com.uma.tfg.entities.Project;
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.TaskImage;
import com.uma.tfg.entities.User;
import com.uma.tfg.services.ActivityService;
import com.uma.tfg.services.ProjectService;
import com.uma.tfg.services.TaskImageService;
import com.uma.tfg.services.TaskService;
import com.uma.tfg.services.UserService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskImageService taskImageService;
    private final ActivityService activityService;
    
    public TaskController(TaskService taskService, ProjectService projectService, UserService userService, TaskImageService taskImageService, ActivityService activityService) { 
    	this.taskService = taskService; 
    	this.userService = userService;
    	this.projectService = projectService;
    	this.taskImageService = taskImageService;
    	this.activityService = activityService;
	}

    @PostMapping("/task")
    public void createTask(@RequestBody Task task) throws Exception {

    	task.setFlagActive(1);
    	
    	if(task.getProject() != null) {
    		if(task.getProject().getId() != null) {
    			Project proj = projectService.getProject(task.getProject().getId());
    			
    			task.setProject(proj);
    		}
    	}
    	
    	if(task.getCreator() != null) {
    		if(task.getCreator().getId() != null) {
    			User creator = userService.getUser(task.getCreator().getId());
    			task.setCreator(creator);
    		}
    	}
    	
    	if(task.getAssignedUsers() != null) {

    		Set<User> usersRelated = new HashSet<>();
    		task.getAssignedUsers().forEach((user)->{
    			if(user.getId() != null) {
					User userRelated;
					try {
						userRelated = userService.getUser(user.getId());
						usersRelated.add(userRelated);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
        	});
    		
    		task.setAssignedUsers(usersRelated);
    	}
    	
    	task.setCreationDate(LocalDate.now());
    	
        Task taskCreated = taskService.createTask(task);
    	
    	if(taskCreated.getImages() != null) {

    		Set<TaskImage> images = new HashSet<>();
    		
    		task.getImages().forEach((image)->{
    			
    			image.setTask(taskCreated);
    			
    			TaskImage imageCreated = taskImageService.createTaskImage(image);
    			images.add(imageCreated);
        	});
    		
    		taskCreated.setImages(images);
    		System.out.println("llega");
    		taskService.updateAfterCreation(taskCreated);
    	}
    	
    	Activity act = new Activity();
        act.setAction("creado");
        act.setActivityDate(LocalDate.now());
        act.setTask(taskCreated);
        act.setCreator(taskCreated.getCreator());

		Set<User> users = new HashSet<>();
		if(taskCreated.getAssignedUsers() != null) {
			users.addAll(taskCreated.getAssignedUsers());
			
		}
		if(taskCreated.getProject().getUsersRelated() != null) {
			users.addAll(taskCreated.getProject().getUsersRelated());
		}
		
        act.setAssignedUsers(users);
        
        activityService.createActivity(act);
    }

    @PutMapping("/task")
    public void updateTask(@RequestBody Task task) throws Exception {
        taskService.updateTask(task);
    }
    
    @PutMapping("/taskUpdatePriorityState")
    public void updateTaskUpdatePriorityState(@RequestBody Task task) throws Exception {
        taskService.updateTaskUpdatePriorityState(task);
    }
    
    @PutMapping("/updateTaskUsers")
    public void updateTaskUsers(@RequestBody Task task) throws Exception {
        taskService.updateTaskUsers(task);
    }

    @GetMapping("/tasks")
    public List<Task> all() {
        return taskService.getAll();
    }
    
    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable Long id) throws Exception {
        return taskService.getTask(id);
    }
    
    @GetMapping("/tasks/user/{userId}")
    public List<Task> getTasksForUser(@PathVariable Long userId) throws Exception {
        return taskService.getTasksForUser(userId.toString());
    }
    
    @GetMapping("/task/assignedUser")
    public List<Task> getTasksAssignedUser(@RequestBody User user) {
        return taskService.getTasksAssignedUser(user);
    }
    
    @GetMapping("/tasks/project/{id}")
    public List<Task> getTasksAssignedUser(@PathVariable Long id) throws Exception {
    	Project proj = projectService.getProject(id);
    	
        return taskService.getTasksByProject(proj);
    }

    @DeleteMapping("/task/delete/{id}")
    public void deleteTask(@PathVariable Long id) throws Exception {
        taskService.delete(id);
    }
}
