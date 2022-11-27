package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.File;
import com.uma.tfg.repositories.FileRepository;

import payroll.UserNotFoundException;
@Service
@Transactional
public class FileService {
	
	@Autowired
    private FileRepository fileRepository;
	
	public File createFile(File file) {
    	return fileRepository.save(file);
    }
    
    public void updateFile(File file) {
    	fileRepository.save(file);
    }

    public List<File> getAll() {
        return (List<File>) fileRepository.findAll();
    }

    public File getFile(Long id) throws Exception{
        return fileRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	fileRepository.deleteById(id);
    }
}
