package com.uma.tfg.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.File;
import com.uma.tfg.entities.Manual;
import com.uma.tfg.entities.Product;
import com.uma.tfg.repositories.FileRepository;
import com.uma.tfg.repositories.ManualRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ManualService {
	
	@Autowired
    private ManualRepository manualRepository;
	@Autowired
    private FileRepository fileRepository;
	
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
    
    public List<Manual> findByProduct(Product product){
        return (List<Manual>) manualRepository.findByProduct(product);
    }
    
    public void delete(Long id) throws Exception {
    	Optional<Manual> man = manualRepository.findById(id);
    	Optional<File> file = fileRepository.findById(man.get().getFile().getId());
    	fileRepository.deleteById(file.get().getId());
    	
    	man.get().setProduct(null);
    	manualRepository.save(man.get());
    	
    	manualRepository.deleteById(id);
    }
}
