package com.uma.tfg.repositories;  

import com.uma.tfg.entities.ProductImage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

	@Query( value = "select pi.url from product p join product_image pi on p.profile_image_id = pi.id ORDER BY rating DESC", nativeQuery = true)
	List<String> findImageTop3Products();
	
}
