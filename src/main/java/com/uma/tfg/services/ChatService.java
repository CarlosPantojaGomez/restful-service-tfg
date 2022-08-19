package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Chat;
import com.uma.tfg.repositories.ChatRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ChatService {
	@Autowired
    private ChatRepository chatRepository;
	
	public Chat createChat(Chat chat) {
    	return chatRepository.save(chat);
    }
    
    public void updateChat(Chat chat) {
    	chatRepository.save(chat);
    }

    public List<Chat> getAll() {
        return (List<Chat>) chatRepository.findAll();
    }

    public Chat getChat(Long id) throws Exception{
        return chatRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public void delete(Long id) throws Exception {
    	chatRepository.deleteById(id);
    }
}
