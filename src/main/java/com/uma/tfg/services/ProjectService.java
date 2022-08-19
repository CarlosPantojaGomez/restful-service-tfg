package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Project;
import com.uma.tfg.repositories.ProjectRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ProjectService {
	@Autowired
    private ProjectRepository projectRepository;
	
	public Project createProject(Project project) {
    	return projectRepository.save(project);
    }
    
    public void updateProject(Project project) {
    	projectRepository.save(project);
    }

    public List<Project> getAll() {
        return (List<Project>) projectRepository.findAll();
    }

    public Project getProject(Long id) throws Exception{
        return projectRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	projectRepository.deleteById(id);
    }
}
