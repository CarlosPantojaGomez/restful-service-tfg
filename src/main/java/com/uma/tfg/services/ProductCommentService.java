package com.uma.tfg.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductComment;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.ProductCommentRepository;
import com.uma.tfg.repositories.ProductRepository;
import com.uma.tfg.repositories.UserRepository;

import payroll.UserNotFoundException;

@Service
@Transactional
public class ProductCommentService {    
	
	@Autowired
    private ProductCommentRepository productCommentRepository;
	@Autowired
    private ProductRepository productRepository;
	@Autowired
    private UserRepository userRepository;
	
	public Product createProductComment(ProductComment productComment) {
    	
    	if(productComment.getProduct() != null) {

    		Optional<Product> product = productRepository.findById(productComment.getProduct().getId());
    		
    		productComment.setProduct(product.get());
    	}
    	
    	if(productComment.getCreator() != null) {

    		Optional<User> creator = userRepository.findById(productComment.getCreator().getId());
    		
    		productComment.setCreator(creator.get());
    	}
    	productComment.setCreationDate(LocalDate.now());
    	
    	ProductComment productCommentCreated = productCommentRepository.save(productComment);

    	Optional<Product> product = java.util.Optional.empty();
    	
    	if(productCommentCreated.getProduct() != null) {

    		product = productRepository.findById(productCommentCreated.getProduct().getId());
    		

    		Set<ProductComment> comments = new HashSet<>();
    		comments = product.get().getComments();
    		comments.add(productCommentCreated);
    		
    		product.get().setComments(comments);
    		
    		productRepository.save(product.get());
    	}


    	Optional<User> creator = java.util.Optional.empty();
    	if(productCommentCreated.getCreator() != null) {

    		creator = userRepository.findById(productCommentCreated.getCreator().getId());
    		

    		Set<ProductComment> comments = new HashSet<>();
    		comments = creator.get().getProductsComments();
    		comments.add(productCommentCreated);
    		
    		creator.get().setProductsComments(comments);
    		
    		userRepository.save(creator.get());
    	}
    	
    	return product.get();
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
