package com.uma.tfg.repositories;

import com.uma.tfg.entities.Product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	@Modifying
	@Query("update Product product set product.flagActive = :flagActive where product.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);

}
