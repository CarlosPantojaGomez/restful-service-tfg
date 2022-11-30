package com.uma.tfg.controllers;

import com.uma.tfg.entities.Project;
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;
import com.uma.tfg.services.ProjectService;
import com.uma.tfg.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    public TaskController(TaskService taskService, ProjectService projectService) { this.taskService = taskService; this.projectService = projectService;}

    @PostMapping("/task")
    public void createTask(@RequestBody Task task) throws Exception {
    	if(task.getProject() != null) {
    		if(task.getProject().getId() != null) {
    			Project proj = projectService.getProject(task.getProject().getId());
    			
    			task.setProject(proj);
    		}
    	}
        taskService.createTask(task);
    }

    @PutMapping("/task")
    public void updateTask(@RequestBody Task task) throws Exception {
        taskService.updateTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> all() {
        return taskService.getAll();
    }
    
    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable Long id) throws Exception {
        return taskService.getTask(id);
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
