package com.uma.tfg.repositories;


import org.springframework.data.repository.CrudRepository;

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductRate;
import com.uma.tfg.entities.User;

public interface ProductRateRepository extends CrudRepository<ProductRate, Long>{

    public ProductRate findByProductAndRater(Product product, User rater);
}
