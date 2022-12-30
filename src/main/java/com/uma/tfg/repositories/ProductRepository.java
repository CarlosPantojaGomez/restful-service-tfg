package com.uma.tfg.repositories;

import com.uma.tfg.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

    public List<Product> findAllByFlagActive(Integer flag);
    
	@Modifying
	@Query("update Product product set product.flagActive = :flagActive where product.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);
	
	@Query( value = "SELECT * FROM Product WHERE product.flag_active = 1 ORDER BY RAND() LIMIT 3", nativeQuery = true)
	List<Product> getImagesTop();

	 @Query( value = "select top (6) * from Product product Where product.name like %:input% and product.flag_active = 1", nativeQuery = true)
	List<Product> getCoincidencesByName(@Param("input") String input);
	 
}
