package com.uma.tfg.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uma.tfg.entities.New;

public interface NewRepository extends CrudRepository<New, Long>  {
	
	@Modifying
	@Query("update New notisia set notisia.flagActive = :flagActive where notisia.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);
}
