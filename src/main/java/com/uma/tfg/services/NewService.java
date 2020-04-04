package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.New;
import com.uma.tfg.repositories.NewRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class NewService {

	@Autowired
    private NewRepository newRepository;
	
	public void createNew(New new1) {
		newRepository.save(new1);
    }

    public New getNew(Long id) throws Exception{
        return newRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<New> getAll(){
        return (List<New>) newRepository.findAll();
    }
    
    public void delete(Long id) throws Exception {
    	newRepository.deleteById(id);
    }
}
