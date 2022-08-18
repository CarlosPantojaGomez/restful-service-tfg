package com.uma.tfg.repositories;

import com.uma.tfg.entities.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	

}
