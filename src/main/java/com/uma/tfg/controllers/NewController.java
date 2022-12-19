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

import com.uma.tfg.entities.New;
import com.uma.tfg.services.NewService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class NewController {

    private final NewService newService;

    public NewController(NewService newService) { this.newService = newService; }

    @PostMapping("/new")
    public void createNoticia(@RequestBody New new1) throws Exception {
    	newService.createNew(new1);
    }
    
    @PutMapping("/new")
    public void updateProduct(@RequestBody New new1) throws Exception {
    	newService.createNew(new1);
    }
    
    @GetMapping("/new/{id}")
    public New getNew(@PathVariable Long id) throws Exception {
        return newService.getNew(id);
    }
    
    @GetMapping("/news/{productId}")
    public List<New> getNewsByProductId(@PathVariable String productId) throws Exception {
        return newService.getNewsByProductId(productId);
    }

    @GetMapping("/news")
    public List<New> all() {
        return newService.getAll();
    }
    
    @DeleteMapping("/new/delete/{id}")
    public void deleteNew(@PathVariable Long id) throws Exception {
    	newService.delete(id);
    }
}
