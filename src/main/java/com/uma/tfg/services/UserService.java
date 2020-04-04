package com.uma.tfg.services;

import com.uma.tfg.entities.User;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.findById(user.getId());
    }

    public void deleteUser(Long id) {
        userRepository.setFlagActive(0, id);
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
