package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Task;
import com.uma.tfg.entities.TaskImage;
import com.uma.tfg.repositories.TaskImageRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class TaskImageService {
	
	@Autowired
    private TaskImageRepository taskImageRepository;

    public TaskImage createTaskImage(TaskImage taskImage) {
    	return taskImageRepository.save(taskImage);
    }
    
    public void updateTaskImage(TaskImage taskImage) {
    	taskImageRepository.save(taskImage);
    }

    public List<TaskImage> getAll() {
        return (List<TaskImage>) taskImageRepository.findAll();
    }
    
    public List<TaskImage> getImagesByTask(Task task) {
        return (List<TaskImage>) taskImageRepository.findByTask(task);
    }


    public TaskImage getTaskImage(Long id) throws Exception{
        return taskImageRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	taskImageRepository.deleteById(id);
    }
}
