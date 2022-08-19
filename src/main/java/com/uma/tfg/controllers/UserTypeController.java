package com.uma.tfg.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uma.tfg.entities.UserType;
import com.uma.tfg.services.UserTypeService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class UserTypeController {
	
	private final UserTypeService userTypeService;
    
    public UserTypeController(UserTypeService userTypeService) { this.userTypeService = userTypeService;}

    @PostMapping("/userType")
    public void createUserType(@RequestBody UserType request) throws Exception {

    	userTypeService.createUserType(request);
    }
    
    @PutMapping("/userType")
    public void updateUserType(@RequestBody UserType request) throws Exception {
    	userTypeService.createUserType(request);
    }
    
    @GetMapping("/userType/{id}")
    public UserType getUserType(@PathVariable Long id) throws Exception {
        return userTypeService.getUserType(id);
    }

    @GetMapping("/userTypes")
    public List<UserType> all() {
        return userTypeService.getAll();
    }

    @DeleteMapping("/userType/delete/{id}")
    public void deleteUserType(@PathVariable Long id) throws Exception {
    	userTypeService.delete(id);
    }
}
