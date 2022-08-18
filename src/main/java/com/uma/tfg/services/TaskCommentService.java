package com.uma.tfg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uma.tfg.entities.TaskComment;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.TaskCommentRepository;

import payroll.UserNotFoundException;

public class TaskCommentService {
	
	@Autowired
    private TaskCommentRepository taskCommentRepository;

    public TaskComment createTaskComment(TaskComment taskImage) {
    	return taskCommentRepository.save(taskImage);
    }
    
    public void updateTaskComment(TaskComment taskImage) {
    	taskCommentRepository.save(taskImage);
    }

    public List<TaskComment> getAll() {
        return (List<TaskComment>) taskCommentRepository.findAll();
    }
    
    public List<TaskComment> getTaskCommentByTaskId(User user) throws Exception{
        return (List<TaskComment>) taskCommentRepository.findByCreator(user);
    }

    public TaskComment getTaskComment(Long id) throws Exception{
        return taskCommentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	taskCommentRepository.deleteById(id);
    }
}
