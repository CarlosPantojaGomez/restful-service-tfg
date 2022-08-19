package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.UserType;
import com.uma.tfg.repositories.UserTypeRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class UserTypeService {
	@Autowired
    private UserTypeRepository userTypeRepository;
	
	public UserType createUserType(UserType userType) {
    	return userTypeRepository.save(userType);
    }
    
    public void updateUserType(UserType userType) {
    	userTypeRepository.save(userType);
    }

    public List<UserType> getAll() {
        return (List<UserType>) userTypeRepository.findAll();
    }

    public UserType getUserType(Long id) throws Exception{
        return userTypeRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	userTypeRepository.deleteById(id);
    }
}
