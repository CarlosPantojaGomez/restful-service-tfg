package com.uma.tfg.services;

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductImage;
import com.uma.tfg.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.UserNotFoundException;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    public List<ProductImage> findByImageTypeAndProduct(Integer type, Product product){
        return (List<ProductImage>) productImageRepository.findByImageTypeAndProduct(type, product);
    }
    
    public List<ProductImage> getAll(){
        return (List<ProductImage>) productImageRepository.findAll();
    }
    
    public List<String> getTop(){
    	List<ProductImage> foo = (List<ProductImage>) productImageRepository.findImageTop3Products();

    	List<String> images = new ArrayList<String>();
		
    	foo.forEach((image)->{
        	images.add(image.getUrl());
    	});
        return images;
    }
    
    public void delete(Long id) throws Exception {
    	Optional<ProductImage> pr = productImageRepository.findById(id);
    	pr.get().setProduct(null);
    	
    	productImageRepository.save(pr.get());
    	
    	productImageRepository.deleteById(id);
    }
}