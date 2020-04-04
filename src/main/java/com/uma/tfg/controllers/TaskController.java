package com.uma.tfg.controllers;

import com.uma.tfg.entities.Mail;
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;
import com.uma.tfg.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class TaskController {

    private final TaskService taskService;

    public TaskController( TaskService taskService) { this.taskService = taskService;}

    @PostMapping("/task")
    public void createTask(@RequestBody Task task) throws Exception {
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

    @GetMapping("/task/assignedUser")
    public List<Task> getTasksAssignedUser(@RequestBody User user) {
        return taskService.getTasksAssignedUser(user);
    }

    @DeleteMapping("/task/delete/{id}")
    public void deleteTask(@PathVariable Long id) throws Exception {
        taskService.delete(id);
    }
}
