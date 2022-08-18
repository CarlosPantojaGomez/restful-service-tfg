package com.uma.tfg.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uma.tfg.entities.TaskComment;
import com.uma.tfg.services.TaskCommentService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class TaskCommentController {
private final TaskCommentService taskCommentService;
    
    public TaskCommentController(TaskCommentService taskCommentService) { this.taskCommentService = taskCommentService;}
    
    @PostMapping("/taskComment")
    public void createTaskComment(@RequestBody TaskComment request) throws Exception {

    	taskCommentService.createTaskComment(request);
}
    
    @PutMapping("/taskComment")
    public void updateTaskComment(@RequestBody TaskComment country) throws Exception {
    	taskCommentService.createTaskComment(country);
    }
    
    @GetMapping("/taskComment/{id}")
    public TaskComment getTaskComment(@PathVariable Long id) throws Exception {
        return taskCommentService.getTaskComment(id);
    }

    @GetMapping("/taskComments")
    public List<TaskComment> all() {
        return taskCommentService.getAll();
    }

    @DeleteMapping("/taskComment/delete/{id}")
    public void deleteTaskComment(@PathVariable Long id) throws Exception {
    	taskCommentService.delete(id);
    }
}
