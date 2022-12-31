package com.uma.tfg.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Basket;
import com.uma.tfg.entities.Bill;
import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.PurchaseRequest;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.BasketRepository;
import com.uma.tfg.repositories.BillRepository;
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
    @Autowired
    private BillRepository billRepository;
    
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
    
    public Basket buyProducts(PurchaseRequest request) throws InterruptedException {
    	//GET NECESARY DATA
    	Optional<Basket> basket = basketRepository.findById(request.getBasket().getId());
    	Optional<User> user = userRepository.findById(request.getBasket().getUser().getId());

		
    	//ADD PRODUCTS BOUGHT TO USER
    	Set<Product> products = user.get().getProductsBought();
    	products.addAll(basket.get().getProducts());
    	
    	user.get().setProductsBought(products);
    	
    	userRepository.save(user.get());

    	totalAmount = 0.0;
    	
    	request.getBasket().getProducts().forEach((product)->{
			if(!product.equals(null)) {

				final Double precioProduct = product.getPrice();
				
				totalAmount += precioProduct;
			}
    		

    		
    	});
    	//CREATE BILL
    	Bill bill = new Bill();
    	bill.setBillNumber(java.util.UUID.randomUUID().toString());
    	bill.setAddress_line_1(request.getAddress_line_1());
    	bill.setAddress_line_2(request.getAddress_line_2());
    	bill.setAdmin_area_1(request.getAdmin_area_1());
    	bill.setAdmin_area_2(request.getAdmin_area_2());
		bill.setCif("FAKE CIF");
		bill.setCompanyAddress("COMPANY ADDRESS");
		bill.setCompanyName("COMPANY NAME");
		bill.setCountry_code(request.getCountry_code());
    	bill.setEmail_address(request.getEmail_address());
    	bill.setGrossValue(totalAmount);
    	bill.setIva(0.0);
    	bill.setName(request.getName());
    	bill.setNational_number(request.getNational_number());
    	bill.setNetValue(totalAmount);
    	bill.setPostal_code(request.getPostal_code());
    	bill.setProducts(basket.get().getProducts());
    	bill.setSaleDate(LocalDate.now());
    	bill.setSurname(request.getSurname());
    	bill.setUser(user.get());
    	
    	billRepository.save(bill);
    	

    	Set<Bill> bills = user.get().getBills();
    	bills.add(bill);
    	user.get().setBills(bills);
    	
    	userRepository.save(user.get());
    	
    	//CLEAN BASKET
    	deleteBasket(basket.get().getId());
    	
    	user.get().setBasket(null);
    	userRepository.save(user.get());

    	
    	return basket.get();
    }
    
}
