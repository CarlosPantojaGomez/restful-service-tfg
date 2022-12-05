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

    public Task createTaskComment(TaskComment taskImage) {
    	
    	if(taskImage.getTask() != null) {

    		Optional<Task> task = taskRepository.findById(taskImage.getTask().getId());
    		
    		taskImage.setTask(task.get());
    	}
    	
    	if(taskImage.getCreator() != null) {

    		Optional<User> creator = userRepository.findById(taskImage.getCreator().getId());
    		
    		taskImage.setCreator(creator.get());
    	}
    	taskImage.setCreationDate(LocalDate.now());
    	
    	TaskComment taskCommentCreated = taskCommentRepository.save(taskImage);
    	
    	 Activity act = new Activity();
         act.setAction("escrito un comentario");
         act.setActivityDate(LocalDate.now());
         act.setCreator(taskCommentCreated.getCreator());

    	Optional<Task> task = java.util.Optional.empty();
    	
    	if(taskCommentCreated.getTask() != null) {

    		task = taskRepository.findById(taskCommentCreated.getTask().getId());
    		

    		Set<TaskComment> comments = new HashSet<>();
    		comments = task.get().getComments();
    		comments.add(taskCommentCreated);
    		
    		task.get().setComments(comments);
    		
    		taskRepository.save(task.get());
    	}

    	act.setTask(task.get());
    	act.setAssignedUsers(task.get().getAssignedUsers());
         
    	if(taskCommentCreated.getCreator() != null) {

    		Optional<User> creator = userRepository.findById(taskCommentCreated.getCreator().getId());
    		

    		Set<TaskComment> comments = new HashSet<>();
    		comments = creator.get().getTasksComments();
    		comments.add(taskCommentCreated);
    		
    		creator.get().setTasksComments(comments);
    		
    		userRepository.save(creator.get());
    	}
    	
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
