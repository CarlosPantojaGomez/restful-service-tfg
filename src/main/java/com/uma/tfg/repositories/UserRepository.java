package com.uma.tfg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uma.tfg.entities.User;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByEmailAndFlagActive(String email, Integer flag);

    List<User> findByUserTypeAndFlagActive(Integer type, Integer flag);

    List<User> findByFlagActive(Integer flag);
    

    List<User> findByNicknameLike(String nickname);

    User findByNicknameAndFlagActive(String nickname, Integer flag);
    
    User findByNicknameAndPasswordAndFlagActive(String nickname, String password, Integer flag);
 
    @Query("update User user set user.flagActive = :flagActive where user.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);
    
    @Query( value = "select top (6) * from user u Where u.nickname like %:input% ", nativeQuery = true)
	List<User> getCoincidencesByNickname(@Param("input") String input);
    
    @Query( value = "select top (6) * from user u Where u.nickname like %:input% and u.user_type = 3 or user_type = 2", nativeQuery = true)
	List<User> getCoincidencesByNicknameForProject(@Param("input") String input);
    
    @Query( value = "select top (6) * from user u join PROJECT_ASSIGNED prj on u.id = prj.user_id Where u.nickname like %:input% and prj.project_id = :projectId", nativeQuery = true)
	List<User> getCoincidencesForTaskByNickname(@Param("input") String input, @Param("projectId") long projectId);

}
