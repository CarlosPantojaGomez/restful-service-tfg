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

import com.uma.tfg.entities.Gender;
import com.uma.tfg.services.GenderService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class GenderController {

	private final GenderService genderService;
    
    public GenderController(GenderService genderService) { this.genderService = genderService;}

    @PostMapping("/gender")
    public void createGender(@RequestBody Gender request) throws Exception {

    	genderService.createGender(request);
    }
    
    @PutMapping("/gender")
    public void updateGender(@RequestBody Gender request) throws Exception {
    	genderService.createGender(request);
    }
    
    @GetMapping("/gender/{id}")
    public Gender getGender(@PathVariable Long id) throws Exception {
        return genderService.getGender(id);
    }

    @GetMapping("/genders")
    public List<Gender> all() {
        return genderService.getAll();
    }

    @DeleteMapping("/gender/delete/{id}")
    public void deleteGender(@PathVariable Long id) throws Exception {
    	genderService.delete(id);
    }

}
