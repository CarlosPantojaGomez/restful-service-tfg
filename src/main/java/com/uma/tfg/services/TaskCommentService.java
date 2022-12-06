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
import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.TaskComment;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.ActivityRepository;
import com.uma.tfg.repositories.TaskCommentRepository;
import com.uma.tfg.repositories.TaskRepository;
import com.uma.tfg.repositories.UserRepository;

import payroll.UserNotFoundException;
@Service
@Transactional
public class TaskCommentService {
	
	@Autowired
    private TaskCommentRepository taskCommentRepository;
	@Autowired
    private TaskRepository taskRepository;
	@Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;

    public Task createTaskComment(TaskComment taskComment) {
    	
    	if(taskComment.getTask() != null) {

    		Optional<Task> task = taskRepository.findById(taskComment.getTask().getId());
    		
    		taskComment.setTask(task.get());
    	}
    	
    	if(taskComment.getCreator() != null) {

    		Optional<User> creator = userRepository.findById(taskComment.getCreator().getId());
    		
    		taskComment.setCreator(creator.get());
    	}
    	taskComment.setCreationDate(LocalDate.now());
    	
    	TaskComment taskCommentCreated = taskCommentRepository.save(taskComment);

    	Optional<Task> task = java.util.Optional.empty();
    	
    	if(taskCommentCreated.getTask() != null) {

    		task = taskRepository.findById(taskCommentCreated.getTask().getId());
    		

    		Set<TaskComment> comments = new HashSet<>();
    		comments = task.get().getComments();
    		comments.add(taskCommentCreated);
    		
    		task.get().setComments(comments);
    		
    		taskRepository.save(task.get());
    	}


    	Optional<User> creator = java.util.Optional.empty();
    	if(taskCommentCreated.getCreator() != null) {

    		creator = userRepository.findById(taskCommentCreated.getCreator().getId());
    		

    		Set<TaskComment> comments = new HashSet<>();
    		comments = creator.get().getTasksComments();
    		comments.add(taskCommentCreated);
    		
    		creator.get().setTasksComments(comments);
    		
    		userRepository.save(creator.get());
    	}
    	

    	
    	Activity act = new Activity();
        act.setAction("escrito un comentario en");
        act.setActivityDate(LocalDate.now());
        act.setTask(task.get());
        act.setCreator(creator.get());
        
        Set<User> users = new HashSet<>();
        
		if(task.get().getAssignedUsers() != null) {
			users.addAll(task.get().getAssignedUsers());
			
		}
		if(task.get().getProject().getUsersRelated() != null) {
			users.addAll(task.get().getProject().getUsersRelated());
		}
		
        act.setAssignedUsers(users);
        
        activityRepository.save(act);
    	
    	return task.get();
    }
    
    public void updateTaskComment(TaskComment taskImage) {
    	taskCommentRepository.save(taskImage);
    }

    public List<TaskComment> getAll() {
        return (List<TaskComment>) taskCommentRepository.findAll();
    }
    
    public List<TaskComment> getTaskCommentByTaskId(Task task) throws Exception{
        return (List<TaskComment>) taskCommentRepository.findByTask(task);
    }

    public TaskComment getTaskComment(Long id) throws Exception{
        return taskCommentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	taskCommentRepository.deleteById(id);
    }
}
