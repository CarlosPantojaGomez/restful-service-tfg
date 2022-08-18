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

import com.uma.tfg.entities.TaskImage;
import com.uma.tfg.services.TaskImageService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class TaskImageController {
    private final TaskImageService taskImageService;
    
    public TaskImageController(TaskImageService taskImageService) { this.taskImageService = taskImageService;}
    
    @PostMapping("/taskImage")
    public void createCountry(@RequestBody TaskImage request) throws Exception {

    	taskImageService.createTaskImage(request);
}
    
    @PutMapping("/taskImage")
    public void updateCountry(@RequestBody TaskImage country) throws Exception {
    	taskImageService.createTaskImage(country);
    }
    
    @GetMapping("/taskImage/{id}")
    public TaskImage getCountry(@PathVariable Long id) throws Exception {
        return taskImageService.getTaskImage(id);
    }

    @GetMapping("/taskImages")
    public List<TaskImage> all() {
        return taskImageService.getAll();
    }

    @DeleteMapping("/taskImage/delete/{id}")
    public void deleteCountry(@PathVariable Long id) throws Exception {
    	taskImageService.delete(id);
    }

}
