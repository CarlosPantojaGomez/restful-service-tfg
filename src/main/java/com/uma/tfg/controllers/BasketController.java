package com.uma.tfg.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uma.tfg.entities.Basket;
import com.uma.tfg.entities.PurchaseRequest;
import com.uma.tfg.services.BasketService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class BasketController {
	
private final BasketService basketService;
    
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }
    
    @GetMapping("/basket/{userId}")
    public Basket getbasketByUserId(@PathVariable String userId) throws Exception {
        return basketService.getBasketByUserId(userId);
    }
    
    @PostMapping("/basket")
    public void createBasket(@RequestBody Basket basket) throws Exception {
    	basketService.createBasket(basket);
    }
    
    @PutMapping("/basket")
    public void updateBasket(@RequestBody Basket basket) throws Exception {
    	basketService.updateBasket(basket);
    }
    
    @PutMapping("/basket/buy")
    public void buyProducts(@RequestBody PurchaseRequest basket) throws Exception {
    	basketService.buyProducts(basket);
    }
    
    @DeleteMapping("/basket/delete/{id}")
    public void deletebasket(@PathVariable Long id) throws Exception {
    	basketService.deleteBasket(id);
    }
}
