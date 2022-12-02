package com.uma.tfg.repositories;

import com.uma.tfg.entities.Mail;
import com.uma.tfg.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MailRepository extends CrudRepository<Mail, Long> {

    public List<Mail> findByReceiverOrderByIdDesc(User user);

    public List<Mail> findByWriter(User user);
}
