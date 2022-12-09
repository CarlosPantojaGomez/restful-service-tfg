package com.uma.tfg.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.New;
import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.NewRepository;
import com.uma.tfg.repositories.ProductRepository;
import com.uma.tfg.repositories.UserRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class NewService {

	@Autowired
    private NewRepository newRepository;
	@Autowired
    private ProductRepository productRepository;
	@Autowired
    private UserRepository userRepository;
	
	public void createNew(New new1) {
		if(new1.getProduct() != null) {
			if(new1.getProduct().getId() != null) {
				Optional<Product> auxProd = productRepository.findById(new1.getProduct().getId());
				new1.setProduct(auxProd.get());
	    	}
    	}
		
		User creator = userRepository.findByNicknameAndFlagActive(new1.getCreator().getNickname(), 1);
		new1.setCreator(creator);
		new1.setCreationDate(LocalDate.now());
		
		newRepository.save(new1);
    }

    public New getNew(Long id) throws Exception{
        return newRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<New> getAll(){
        return (List<New>) newRepository.findAll();
    }
    
    public void delete(Long id) throws Exception {
    	newRepository.deleteById(id);
    }
}
