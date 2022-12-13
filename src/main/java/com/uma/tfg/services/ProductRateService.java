package com.uma.tfg.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.ProductRate;
import com.uma.tfg.repositories.ProductRateRepository;

@Service
@Transactional
public class ProductRateService {
    @Autowired
    private ProductRateRepository productRateRepository;

    public void createProductRate(ProductRate productRate) {
    	productRateRepository.save(productRate);
    }
}
