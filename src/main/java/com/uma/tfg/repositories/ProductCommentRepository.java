package com.uma.tfg.repositories;

import com.uma.tfg.entities.ProductComment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductCommentRepository extends CrudRepository<ProductComment, Long> {
	
	@Query( value = "select * from product_comment where product = :id", nativeQuery = true)
	List<ProductComment> getProductCommentsByProductId(@Param("id") Long id);
}
