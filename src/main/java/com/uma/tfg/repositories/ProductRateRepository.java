package com.uma.tfg.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductRate;
import com.uma.tfg.entities.User;

public interface ProductRateRepository extends CrudRepository<ProductRate, Long>{

    public ProductRate findByProductAndRater(Product product, User rater);
    
    public List<ProductRate> findByProduct(Product product);
}
