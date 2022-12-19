package com.uma.tfg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uma.tfg.entities.New;

public interface NewRepository extends CrudRepository<New, Long>  {
	
	@Modifying
	@Query("update New notisia set notisia.flagActive = :flagActive where notisia.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);
	
	@Query( value = "select * from New noticia Where noticia.id in (SELECT new_id FROM NEW_PRODUCT_RELATED  where product_id = :id) ", nativeQuery = true)
	List<New> getNewsByProduct(@Param("id") String id);
}
