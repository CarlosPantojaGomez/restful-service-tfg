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
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.ActivityRepository;
import com.uma.tfg.repositories.ProjectRepository;
import com.uma.tfg.repositories.UserRepository;


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
		project.setFlagActive(1);
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
    	Optional<Project> proyecto = projectRepository.findById(project.getId());
    	
    	proyecto.get().setDescription(project.getDescription());
    	proyecto.get().setName(project.getName());
    	proyecto.get().setPriority(project.getPriority());

		Set<User> users = new HashSet<>();
		
		project.getUsersRelated().forEach((user)->{
			System.out.println(user.getId());
			Optional<User> userRelated = userRepository.findById(user.getId());
			users.add(userRelated.get());
    	});
		System.out.println(users.size());
		
		proyecto.get().setUsersRelated(users);
		
    	projectRepository.save(proyecto.get());
    	
    	Activity act = new Activity();
        act.setAction("modificado");
        act.setActivityDate(LocalDate.now());
        act.setProject(project);
        act.setCreator(proyecto.get().getCreator());

        act.setAssignedUsers(proyecto.get().getUsersRelated());
        
        activityRepository.save(act);
    }

    public List<Project> getAll() {
        return (List<Project>) projectRepository.findAllByFlagActive(1);
    }
    
    public List<Project> getProjectsForUser(String userId) {
        return (List<Project>) projectRepository.getProjectsForUser(userId);
    }

    public Project getProject(Long id) throws Exception{
    	Project p = projectRepository.findByIdAndFlagActive(id, 1);
    	if (p != null) {
    		Set<Activity> activities = new HashSet<>();
    		
    		p.getActivities().forEach((activity)->{
    			if(activity.getFlagActive() == 1) {
    				activities.add(activity);
    			}
        	});
    		
    		p.setActivities(activities);
    		
    		Set<Task> tasks = new HashSet<>();
    		
    		p.getTasks().forEach((task)->{
    			if(task.getFlagActive() == 1) {
    				tasks.add(task);
    			}
        	});
    		
    		p.setTasks(tasks);
    		
    		Set<User> usersRelated = new HashSet<>();
    		
    		p.getUsersRelated().forEach((user)->{
    			if(user.getFlagActive() == 1) {
    				usersRelated.add(user);
    			}
        	});
    		
    		p.setUsersRelated(usersRelated);
    		
            return p;
    	} else {
    		return null;
    	}
    	
    }
    
    public void delete(Long id) throws Exception {
    	Optional<Project> p = projectRepository.findById(id); 
    	p.get().setUsersRelated(null);
    	p.get().setCreator(null);
    	
    	Project saved = projectRepository.save(p.get());
    	Set<Project> projects = new HashSet<>();
    	projects.add(saved);

    	projectRepository.delete(saved);
    }
}
