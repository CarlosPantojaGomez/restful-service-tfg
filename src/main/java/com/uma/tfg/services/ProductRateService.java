package com.uma.tfg.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductRate;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.ProductRateRepository;
import com.uma.tfg.repositories.ProductRepository;
import com.uma.tfg.repositories.UserRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ProductRateService {
    @Autowired
    private ProductRateRepository productRateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductRate createProductRate(ProductRate productRate) {

        User rater = userRepository.findById(productRate.getRater().getId()).orElseThrow(() -> new UserNotFoundException(productRate.getRater().getId()));
        Product product = productRepository.findById(productRate.getProduct().getId()).orElseThrow(() -> new UserNotFoundException(productRate.getProduct().getId()));
    	
        productRate.setProduct(product);
        productRate.setRater(rater);
        
    	return productRateRepository.save(productRate);
    }
}
