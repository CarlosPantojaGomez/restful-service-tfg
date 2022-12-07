package com.uma.tfg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uma.tfg.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	@Query( value = "select * from project proj where proj.creator_id = :userId or proj.id in (SELECT prjAs.project_id FROM PROJECT_ASSIGNED prjAs where user_id = :userId)", nativeQuery = true)
	List<Project> getProjectsForUser(@Param("userId") String userId);
}
