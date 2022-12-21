package com.uma.tfg.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Basket;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.BasketRepository;
import com.uma.tfg.repositories.ProductRepository;
import com.uma.tfg.repositories.UserRepository;

@Service
@Transactional
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    
    private Double totalAmount;
    
    public Basket createBasket(Basket basket) {
        return basketRepository.save(basket);
    }
    
    public Basket getBasketByUserId(String userId) {
    	Optional<User> user = userRepository.findById(Long.parseLong(userId));
    	
        return basketRepository.findByUser(user.get());
    }
    
    public Basket updateBasket(Basket basket) {
    	Optional<Basket> basketAux = basketRepository.findById(basket.getId());
    	
    	if(basketAux.get().getProducts() != null) {
    		deleteBasket(basket.getId());
    		return null;
    	} else {
    		basketAux.get().getProducts().forEach((product)->{
    			product.setBaskets(null);
    			
        		productRepository.save(product);
        		
        	});
    		
    		totalAmount = 0.0;
    		basket.getProducts().forEach((product)->{
    			final Double precioProduct = product.getPrice();
    			
    			totalAmount += precioProduct;
        		
        	});
    		
    		basketAux.get().setProducts(basket.getProducts());
    		basketAux.get().setAmount(totalAmount);
    		
            return basketRepository.save(basket);
    	}	
    }
    
    public void deleteBasket(Long basketId) {
    	
    	Optional<Basket> basket = basketRepository.findById(basketId);
    	basket.get().getProducts().forEach((product)->{
			product.setBaskets(null);
			
    		productRepository.save(product);
    		
    	});
    	
    	basket.get().setProducts(null);
    	basket.get().setUser(null);
    	
    	basketRepository.delete(basket.get());
    }
    
}
