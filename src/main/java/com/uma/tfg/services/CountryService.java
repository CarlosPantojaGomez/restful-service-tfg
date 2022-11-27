package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Country;
import com.uma.tfg.repositories.CountryRepository;

import payroll.UserNotFoundException;
@Service
@Transactional
public class CountryService {
	
	@Autowired
    private CountryRepository countryRepository;

    public Country createCountry(Country country) {
    	return countryRepository.save(country);
    }
    
    public void updateCountry(Country country) {
    	countryRepository.save(country);
    }

    public List<Country> getAll() {
        return (List<Country>) countryRepository.findAll();
    }

    public Country getCountry(Long id) throws Exception{
        return countryRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	countryRepository.deleteById(id);
    }
}
