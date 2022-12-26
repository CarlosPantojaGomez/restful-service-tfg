package com.uma.tfg.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uma.tfg.entities.ProductComment;
import com.uma.tfg.services.ProductCommentService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ProductCommentController {

    private final ProductCommentService productCommentService;
    
    public ProductCommentController(ProductCommentService productCommentService) { this.productCommentService = productCommentService;}
    
	@PostMapping("/productComment")
    public void createProductComment(@RequestBody ProductComment request) throws Exception {
        productCommentService.createProductComment(request);
	}
	
	@GetMapping("/productComment/{id}")
    public ProductComment getProductComment(@PathVariable Long id) throws Exception {
        return productCommentService.getProductComment(id);
    }
	
	@GetMapping("/productCommentsProductId/{id}")
    public List<ProductComment> getProductCommenstByProductId(@PathVariable Long id) throws Exception {
        return productCommentService.getProductCommentsByProductId(id);
    }
	
	@GetMapping("/productComments")
    public List<ProductComment> all() {
        return productCommentService.getAll();
    }

    @DeleteMapping("/productComment/delete/{id}")
    public void deleteProductComment(@PathVariable Long id) throws Exception {
    	productCommentService.delete(id);
    }
}
