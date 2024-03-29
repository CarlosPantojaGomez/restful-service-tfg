package com.uma.tfg.controllers;

import com.uma.tfg.entities.ProductBasketRequest;
import com.uma.tfg.entities.User;
import com.uma.tfg.entities.UserRequest;
import com.uma.tfg.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user) throws Exception {
        userService.createUser(user);
    }
    
    @GetMapping("/user/authenticate/{nickname}/{password}")
    public User AuthUser(@PathVariable String nickname, @PathVariable String password) throws Exception {
        User user = userService.getByNicknameAndPassword(nickname,password);
        
    	return user;
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user) throws Exception {
        userService.updateUser(user);
    }
    
    @PutMapping("/userPassword")
    public void updateUserPassword(@RequestBody User user) throws Exception {
        userService.updateUserPassword(user);
    }
    
    @PutMapping("/user/basket")
    public void addProductToBasket(@RequestBody ProductBasketRequest request) throws Exception {
        userService.addProductToBasket(request);
    }
    
    @DeleteMapping("/user/basket/{productId}/{userId}")
    public void removeProductFromBasket(@PathVariable String productId, @PathVariable String userId) throws Exception {
        userService.removeProductFromBasket(productId, userId);
    }

    @PutMapping("/userDelete/{id}")
    public void deleteUser(@PathVariable String id) throws Exception {
        Long idUser = Long.parseLong(id);
        userService.deleteUser(idUser);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) throws Exception {
    	Long idUser = Long.parseLong(id);
        return userService.getUser(idUser);
    }

    @GetMapping("/user/findEmail/{email}")
    public List<User> getUserByEmail(@PathVariable String email) throws Exception {
        return userService.getByEmail(email);
    }
    
    @GetMapping("/users/findByNickname/{input}")
    public List<User> getCoincidencesbynickname(@PathVariable String input) throws Exception {
        return userService.getcoincidencesByNickname(input);
    }
    
    @GetMapping("/users/findByNicknameForProject/{input}")
    public List<User> getCoincidencesbynicknameforproject(@PathVariable String input) throws Exception {
        return userService.getcoincidencesByNicknameForProject(input);
    }
    
    @GetMapping("/users/findByNicknameForTask/{input}/{projectId}")
    public List<User> getCoincidencesbynickname(@PathVariable String input, @PathVariable String projectId) throws Exception {
        return userService.getCoincidencesForTaskByNickname(input, Long.parseLong(projectId));
    }

    @GetMapping("/users/findType/{type}")
    public List<User> getUserByUserType(@PathVariable String type) throws Exception {
        int option = Integer.parseInt(type);
        return userService.getByUserType(option);
    }

    @GetMapping("/user/findNickname/{nickname}")
    public User getUserByNickname(@PathVariable String nickname) throws Exception {
        return userService.getByNickname(nickname);
    }

    @GetMapping("/users")
    public List<User> all() {
        return userService.getAll();
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Long id) throws Exception {
        userService.delete(id);
    }
    
    public void addProductToCest(@PathVariable UserRequest request) throws Exception {
        userService.addProductToCest(request);
    }
}