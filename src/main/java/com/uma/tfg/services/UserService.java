package com.uma.tfg.services;

import com.uma.tfg.entities.Basket;
import com.uma.tfg.entities.Country;
import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductBasketRequest;
import com.uma.tfg.entities.User;
import com.uma.tfg.entities.UserRequest;
import com.uma.tfg.repositories.BasketRepository;
import com.uma.tfg.repositories.CountryRepository;
import com.uma.tfg.repositories.ProductRepository;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private CountryRepository countryRepository;

    private Double totalAmount;
    
    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User usr) {
    	Optional<User> user = userRepository.findById(usr.getId());

    	if(usr.getCountry() != null) {

        	Optional<Country> country = countryRepository.findById(usr.getCountry().getId());
        	user.get().setCountry(country.get());
    	}
    	
    	user.get().setAddress(usr.getAddress());
    	user.get().setCity(usr.getCity());
    	user.get().setName(usr.getName());
    	user.get().setFirstLastName(usr.getFirstLastName());
    	user.get().setSecondLastName(usr.getSecondLastName());
    	user.get().setProfilePicture(usr.getProfilePicture());
    	user.get().setTlf(usr.getTlf());
    	user.get().setZipcode(usr.getZipcode());
    	
    	userRepository.save(user.get());
    }
    
    public void updateUserPassword(User usr) {
    	Optional<User> user = userRepository.findById(usr.getId());
    	
    	user.get().setPassword(usr.getPassword());
    	
    	userRepository.save(user.get());
    }
    
    public Basket addProductToBasket(ProductBasketRequest request) {
    	
    	Optional<User> user = userRepository.findById(request.getUser().getId());
    	Optional<Product> product = productRepository.findById(request.getProduct().getId());
    	
    	Basket bask = new Basket();
    	System.out.println("La cesta: "+ user.get().getBasket());
    	if(user.get().getBasket() != null) {
    		Optional<Basket> basket = basketRepository.findById(user.get().getBasket().getId());
    		
   		 	Set<Product> cestProducts = user.get().getBasket().getProducts();

   		 	cestProducts.add(product.get());
    		basket.get().setProducts(cestProducts);
    		
    		totalAmount = 0.0;
    		basket.get().getProducts().forEach((productt)->{
    			final Double precioProduct = productt.getPrice();
    			
    			totalAmount += precioProduct;
        		
        	});
    		
    		basket.get().setAmount(totalAmount);
    		Basket bas = basketRepository.save(basket.get());
    		
    		user.get().setBasket(bas);
    		
    		userRepository.save(user.get());
		 
    		return bas;
    	} else {
    		Set<Product> cestProducts  = new HashSet<>();
   		 	cestProducts.add(product.get());
    		//bask.setProducts(cestProducts);
    		//bask.setUser(user.get());
    		System.out.println("LLEGA AQUI 1");
    		Basket bas = basketRepository.save(bask);
    		//bas.setProducts(cestProducts);
    		bas.setUser(user.get());
    		bas.setAmount(product.get().getPrice());
    		
    		bas = basketRepository.save(bask);
    		
    		bas.setProducts(cestProducts);
    		
    		bas = basketRepository.save(bask);
    		
    		user.get().setBasket(bas);
    		
    		userRepository.save(user.get());
    		
    		return bas;
    	}
    }
    
    public Basket removeProductFromBasket(String productId, String userId) {
    	
    	Optional<User> user = userRepository.findById(Long.parseLong(userId));
    	Optional<Product> product = productRepository.findById(Long.parseLong(productId));
    	
    	
    	System.out.println("La cesta: "+ user.get().getBasket());
    	if(user.get().getBasket() != null) {
    		Optional<Basket> basket = basketRepository.findById(user.get().getBasket().getId());
    		if(basket.get().getProducts().size() == 1) {
    			basket.get().setProducts(null);
    			basket.get().setUser(null);
    			basketRepository.save(basket.get());

        		user.get().setBasket(null);
        		
        		userRepository.save(user.get());
        		
        		return null;
    		} else {
    			basket.get().getProducts().remove(product.get());
    			
    			totalAmount = 0.0;
        		basket.get().getProducts().forEach((productt)->{
        			final Double precioProduct = productt.getPrice();
        			
        			totalAmount += precioProduct;
            		
            	});
        		basket.get().setAmount(totalAmount);
        		Basket bas = basketRepository.save(basket.get());
        		user.get().setBasket(bas);
        		
        		userRepository.save(user.get());
        		return bas;
    		}
    	}
    	
    	return null;
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
