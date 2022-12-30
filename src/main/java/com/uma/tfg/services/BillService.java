package com.uma.tfg.services;

import com.uma.tfg.entities.Bill;
import com.uma.tfg.entities.Product;
import com.uma.tfg.repositories.BillRepository;
import com.uma.tfg.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductRepository productRepository;

    public void createMail(Bill bill) {
        billRepository.save(bill);
    }

    public List<Bill> getAll() {
        return (List<Bill>) billRepository.findAll();
    }
    
    public List<Bill> getBillsForProduct(Long productId) {
    	Optional<Product> product = productRepository.findById(productId);
    	
        return (List<Bill>) billRepository.findByProducts(product.get());
    }
    
    public Bill getBillByProductAndUser(String productId, String userId) {
    	return billRepository.getBillByUserAndProduct(productId, userId);
    }
    
    public void delete(Long id) throws Exception {
    	billRepository.deleteById(id);
    }
}
