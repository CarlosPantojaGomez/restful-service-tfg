package com.uma.tfg.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Activity;
import com.uma.tfg.entities.Project;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.ActivityRepository;
import com.uma.tfg.repositories.ProjectRepository;
import com.uma.tfg.repositories.UserRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ProjectService {
	@Autowired
    private ProjectRepository projectRepository;
	@Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;
	
	public Project createProject(Project project) {
		User creator = userRepository.findByNicknameAndFlagActive(project.getCreator().getNickname(), 1);

		Set<User> users = new HashSet<>();
		
		project.getUsersRelated().forEach((user)->{
			Optional<User> userRelated = userRepository.findById(user.getId());
			users.add(userRelated.get());
    	});
		project.setCreator(creator);
		project.setUsersRelated(users);
    	Project p = projectRepository.save(project);
    	
    	Activity act = new Activity();
        act.setAction("creado");
        act.setActivityDate(LocalDate.now());
        act.setProject(project);
        act.setCreator(creator);

        act.setAssignedUsers(users);
        
        activityRepository.save(act);
        
        return p;
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
