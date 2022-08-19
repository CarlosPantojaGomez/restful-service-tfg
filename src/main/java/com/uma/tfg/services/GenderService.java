package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Gender;
import com.uma.tfg.repositories.GenderRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class GenderService {
	@Autowired
    private GenderRepository genderRepository;
	
	public Gender createGender(Gender gender) {
    	return genderRepository.save(gender);
    }
    
    public void updateGender(Gender gender) {
    	genderRepository.save(gender);
    }

    public List<Gender> getAll() {
        return (List<Gender>) genderRepository.findAll();
    }

    public Gender getGender(Long id) throws Exception{
        return genderRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	genderRepository.deleteById(id);
    }
}
