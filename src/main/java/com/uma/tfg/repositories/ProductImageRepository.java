package com.uma.tfg.repositories;  

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductImage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

	@Query( value = "select * from product p join product_image pi on p.id = pi.product_id WHERE pi.image_Type = 2 ORDER BY rating DESC", nativeQuery = true)
	List<ProductImage> findImageTop3Products();
	
	@Query("update ProductImage prImg set prImg.product = null where prImg.id = :id")
    void setProductIdToNull(@Param("id") long id);
	
	List<ProductImage> findByImageTypeAndProduct(Integer imageType, Product product);
	
}
