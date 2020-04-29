package com.uma.tfg.services;

import com.uma.tfg.entities.Product;
import com.uma.tfg.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(Long id) throws Exception{
        return productRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<Product> getAll(){
        return (List<Product>) productRepository.findAll();
    }
    
    public void delete(Long id) throws Exception {
    	productRepository.deleteById(id);
    }
}
