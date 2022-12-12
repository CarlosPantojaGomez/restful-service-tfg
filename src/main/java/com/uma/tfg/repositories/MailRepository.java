package com.uma.tfg.repositories;

import com.uma.tfg.entities.Mail;
import com.uma.tfg.entities.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MailRepository extends CrudRepository<Mail, Long> {

    public List<Mail> findByReceiverOrderByIdDesc(User user);

    public List<Mail> findByWriter(User user);
    
    @Modifying
    @Query("update Mail mail set mail.flagActive = :flagActive where mail.id = :id")
    void setFlagActive(@Param("flagActive") Integer flagActive, @Param("id") Long id);
}
