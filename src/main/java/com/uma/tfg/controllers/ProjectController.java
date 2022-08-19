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

import com.uma.tfg.entities.Project;
import com.uma.tfg.services.ProjectService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ProjectController {
	
	private final ProjectService projectService;
    
    public ProjectController(ProjectService projectService) { this.projectService = projectService;}

    @PostMapping("/project")
    public void createProject(@RequestBody Project request) throws Exception {

    	projectService.createProject(request);
    }
    
    @PutMapping("/project")
    public void updateProject(@RequestBody Project request) throws Exception {
    	projectService.createProject(request);
    }
    
    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable Long id) throws Exception {
        return projectService.getProject(id);
    }

    @GetMapping("/projects")
    public List<Project> all() {
        return projectService.getAll();
    }

    @DeleteMapping("/project/delete/{id}")
    public void deleteProject(@PathVariable Long id) throws Exception {
    	projectService.delete(id);
    }
}
