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

import com.uma.tfg.entities.Manual;
import com.uma.tfg.services.ManualService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ManualController {
	
	private final ManualService manualService;
    
    public ManualController(ManualService manualService) { this.manualService = manualService;}

    @PostMapping("/manual")
    public void createManual(@RequestBody Manual request) throws Exception {

    	manualService.createManual(request);
    }
    
    @PutMapping("/manual")
    public void updateManual(@RequestBody Manual request) throws Exception {
    	manualService.createManual(request);
    }
    
    @GetMapping("/manual/{id}")
    public Manual getManual(@PathVariable Long id) throws Exception {
        return manualService.getManual(id);
    }

    @GetMapping("/manuals")
    public List<Manual> all() {
        return manualService.getAll();
    }

    @DeleteMapping("/manual/delete/{id}")
    public void deleteManual(@PathVariable Long id) throws Exception {
    	manualService.delete(id);
    }
}
