package com.uma.tfg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uma.tfg.entities.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long>{

	@Query( value = "select * from activity act where act.id in (SELECT activity_id FROM USER_ASSIGNED where user_id = :id)", nativeQuery = true)
	List<Activity> getActivitiesByUserId(@Param("id") String id);
	
	@Modifying
    @Query("update Activity activity set activity.flagActive = :flagActive where activity.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);
}
