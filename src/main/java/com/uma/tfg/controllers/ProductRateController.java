package com.uma.tfg.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uma.tfg.entities.ProductRate;
import com.uma.tfg.services.ProductRateService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ProductRateController {
	
    private final ProductRateService productRateService;
    
    public ProductRateController(ProductRateService productRateService) { 
    	this.productRateService = productRateService; 
	}
    
    @PostMapping("/productRate")
    public void createProduct(@RequestBody ProductRate request) throws Exception {

    	productRateService.createProductRate(request);
        
    }
}
