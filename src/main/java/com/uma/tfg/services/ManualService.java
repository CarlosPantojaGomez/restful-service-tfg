package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Manual;
import com.uma.tfg.repositories.ManualRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ManualService {
	@Autowired
    private ManualRepository manualRepository;
	
	public Manual createManual(Manual manual) {
    	return manualRepository.save(manual);
    }
    
    public void updateManual(Manual manual) {
    	manualRepository.save(manual);
    }

    public List<Manual> getAll() {
        return (List<Manual>) manualRepository.findAll();
    }

    public Manual getManual(Long id) throws Exception{
        return manualRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	manualRepository.deleteById(id);
    }
}
