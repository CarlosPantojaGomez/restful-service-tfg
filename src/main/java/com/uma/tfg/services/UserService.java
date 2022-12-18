package com.uma.tfg.services;

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.User;
import com.uma.tfg.entities.UserRequest;
import com.uma.tfg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
    	userRepository.save(user);
    }

    public void deleteUser(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	user.get().setFlagActive(0);
    	userRepository.save(user.get());
    }

    public User getUser(Long id) throws Exception{
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAll(){
        return (List<User>) userRepository.findByFlagActive(1);
    }

    public List<User> getByEmail(String email){
        return (List<User>) userRepository.findByEmailAndFlagActive(email,1);
    }
    
    public List<User> getcoincidencesByNickname(String input){
    	System.out.println(input);
        return (List<User>) userRepository.getCoincidencesByNickname(input);
    } 
    
    public List<User> getcoincidencesByNicknameForProject(String input){
    	System.out.println(input);
        return (List<User>) userRepository.getCoincidencesByNicknameForProject(input);
    } 
    
    public List<User> getCoincidencesForTaskByNickname(String input, long projectId){
        return (List<User>) userRepository.getCoincidencesForTaskByNickname(input, projectId);
    }

    public List<User> getByUserType(Integer type){
        return (List<User>) userRepository.findByUserTypeAndFlagActive(type,1);
    }

    public User getByNickname(String nickname){
        return userRepository.findByNicknameAndFlagActive(nickname,1);
    }
    
    public User getByNicknameAndPassword(String nickname, String password) {
    	return userRepository.findByNicknameAndPasswordAndFlagActive(nickname, password,1);
    }

    public void delete(Long id) throws Exception{
        userRepository.deleteById(id);
    }
    
    public void addProductToCest(UserRequest request) throws Exception{
    	
    	Set<Product>cest = request.getUser().getProductsBought();
    	cest.add(request.getProduct());
    	
    	request.getUser().setProductsBought(cest);
    	
        userRepository.save(request.getUser());
    }

	/*@Override
	public UserDetails loadUserByUsername(String arg0) {
		// TODO Auto-generated method stub
		User us = userRepository.findByNickname(arg0);
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		UserDetails userDet = new org.springframework.security.core.userdetails.User(us.getNickname(), us.getPassword(), roles);
		
		return userDet;
	}*/
}
