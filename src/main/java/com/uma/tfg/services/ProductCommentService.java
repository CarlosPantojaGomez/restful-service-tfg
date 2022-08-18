package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.ProductComment;
import com.uma.tfg.repositories.ProductCommentRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ProductCommentService {    
	
	@Autowired
    private ProductCommentRepository productCommentRepository;
	
	public ProductComment createProductImage(ProductComment productComment) {
    	return productCommentRepository.save(productComment);
    }

    public ProductComment getProductComment(Long id) throws Exception{
        return productCommentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public List<ProductComment> getProductCommentsByProductId(Long id) throws Exception{
        return productCommentRepository.getProductCommentsByProductId(id);
    }

    public List<ProductComment> getAll(){
        return (List<ProductComment>) productCommentRepository.findAll();
    }
    
    public void delete(Long id) throws Exception {
    	productCommentRepository.deleteById(id);
    }

}
