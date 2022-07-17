package com.uma.tfg.services;

import com.uma.tfg.entities.ProductImage;
import com.uma.tfg.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    public ProductImage createProductImage(ProductImage productImage) {
    	return productImageRepository.save(productImage);
    }

    public ProductImage getProductImage(Long id) throws Exception{
        return productImageRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<ProductImage> getAll(){
        return (List<ProductImage>) productImageRepository.findAll();
    }
    
    public List<String> getTop(){
        return (List<String>) productImageRepository.findImageTop3Products();
    }
    
    public void delete(Long id) throws Exception {
    	productImageRepository.deleteById(id);
    }
}