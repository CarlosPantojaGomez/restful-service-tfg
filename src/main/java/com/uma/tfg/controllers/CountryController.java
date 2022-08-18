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

import com.uma.tfg.entities.Country;
import com.uma.tfg.services.CountryService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class CountryController {
	
    private final CountryService countryService;
    
    public CountryController(CountryService countryService) { this.countryService = countryService;}

    @PostMapping("/country")
    public void createCountry(@RequestBody Country request) throws Exception {

    	countryService.createCountry(request);
    }
    
    @PutMapping("/country")
    public void updateCountry(@RequestBody Country country) throws Exception {
    	countryService.createCountry(country);
    }
    
    @GetMapping("/country/{id}")
    public Country getCountry(@PathVariable Long id) throws Exception {
        return countryService.getCountry(id);
    }

    @GetMapping("/countries")
    public List<Country> all() {
        return countryService.getAll();
    }

    @DeleteMapping("/country/delete/{id}")
    public void deleteCountry(@PathVariable Long id) throws Exception {
    	countryService.delete(id);
    }
    
}
