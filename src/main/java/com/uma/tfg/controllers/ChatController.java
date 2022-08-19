package com.uma.tfg.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uma.tfg.entities.Chat;
import com.uma.tfg.services.ChatService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ChatController {
	
	private final ChatService chatService;
    
    public ChatController(ChatService chatService) { this.chatService = chatService;}

    @PostMapping("/chat")
    public void createChat(@RequestBody Chat request) throws Exception {

    	chatService.createChat(request);
    }
    
    @PutMapping("/chat")
    public void updateChat(@RequestBody Chat country) throws Exception {
    	chatService.createChat(country);
    }
    
    @GetMapping("/chat/{id}")
    public Chat getChat(@PathVariable Long id) throws Exception {
        return chatService.getChat(id);
    }

    @GetMapping("/chats")
    public List<Chat> all() {
        return chatService.getAll();
    }

    @DeleteMapping("/chat/delete/{id}")
    public void deleteCountry(@PathVariable Long id) throws Exception {
    	chatService.delete(id);
    }
}
