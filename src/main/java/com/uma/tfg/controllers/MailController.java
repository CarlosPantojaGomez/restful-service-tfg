package com.uma.tfg.controllers;

import com.uma.tfg.entities.Mail;
import com.uma.tfg.entities.User;
import com.uma.tfg.services.MailService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class MailController {
	
	private JavaMailSender javaMailSender;
    private final MailService mailService;

    public MailController(MailService mailService, JavaMailSender javaMailSender) { 
    	this.mailService = mailService;
    	this.javaMailSender = javaMailSender;
    }

    @PostMapping("/mail")
    public void createMail(@RequestBody Mail mail) throws Exception {
        mailService.createMail(mail);
    }

    @GetMapping("/mails")
    public List<Mail> all() {
        return mailService.getAll();
    }

    @GetMapping("/mails_receiver")
    public List<Mail> getMailsReceiver(@RequestBody User user) throws Exception {
        return (List<Mail>) mailService.getMailsReceiver(user);
    }

    @GetMapping("/mails_writer")
    public List<Mail> getMailsWriter(@RequestBody User user) throws Exception {
        return (List<Mail>) mailService.getMailsWriter(user);
    }

    @DeleteMapping("/mail/delete/{id}")
    public void deleteUser(@PathVariable Long id) throws Exception {
        mailService.delete(id);
    }
    @PostMapping("/sendMail/{subject}")
    public void sendEmail(@PathVariable String subject,@RequestBody String text) {
    	SimpleMailMessage mail = new SimpleMailMessage();
    	mail.setTo("cpgtfgadcom2020@gmail.com");
    	mail.setSubject(subject);
    	mail.setText(text);
    	
    	javaMailSender.send(mail);
    }
}
