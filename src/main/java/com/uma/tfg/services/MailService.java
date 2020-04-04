package com.uma.tfg.services;

import com.uma.tfg.entities.Mail;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MailService {

    @Autowired
    private MailRepository mailRepository;

    public void createMail(Mail mail) {
        mailRepository.save(mail);
    }

    public List<Mail> getAll(){
        return (List<Mail>) mailRepository.findAll();
    }

    public List<Mail> getMailsReceiver(User user) throws Exception{
        return (List<Mail>) mailRepository.findByReceiver(user);
    }

    public List<Mail> getMailsWriter(User user) throws Exception{
        return (List<Mail>) mailRepository.findByWriter(user);
    }

    public void delete(Long id) throws Exception{
        mailRepository.deleteById(id);
    }
}
