package com.uma.tfg.services;

import com.uma.tfg.entities.Mail;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.MailRepository;
import com.uma.tfg.repositories.UserRepository;

import payroll.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.time.*;

@Service
@Transactional
public class MailService {

    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private UserRepository userRepository;

    public void createMail(Mail mail) {
    	if(mail.getReceiverName() != null && mail.getWriterName() != null) {
    		User writer = userRepository.findByNicknameAndFlagActive(mail.getReceiverName(), 1);

    		User receiver = userRepository.findByNicknameAndFlagActive(mail.getReceiverName(), 1);
    		
    		if (writer != null && receiver != null) {

        		mail.setWriter(writer);
        		mail.setReceiver(receiver);
        		mail.setCreationDate(LocalDate.now());

                mailRepository.save(mail);
    		}
    	}
    }

    public List<Mail> getAll(){
        return (List<Mail>) mailRepository.findAll();
    }

    public Mail getMail(Long id) throws Exception{
        Mail mail = mailRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        
        mail.setSaw(true);
        
        mailRepository.save(mail);
        
        return mail;
    }

    public List<Mail> getMailsReceiver(User user) throws Exception{
        return (List<Mail>) mailRepository.findByReceiverOrderByIdDesc(user);
    }

    public List<Mail> getMailsWriter(User user) throws Exception{
        return (List<Mail>) mailRepository.findByWriter(user);
    }

    public void delete(Long id) throws Exception{
        mailRepository.deleteById(id);
    }
}
