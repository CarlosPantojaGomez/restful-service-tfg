package com.uma.tfg.controllers;

import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductImage;
import com.uma.tfg.entities.ProductRequest;
import com.uma.tfg.services.ProductImageService;
import com.uma.tfg.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService, ProductImageService productImageService) { this.productService = productService; this.productImageService = productImageService;}

    @PostMapping("/product")
    public void createProduct(@RequestBody ProductRequest request) throws Exception {
        Product prod = productService.createProduct(request.getProduct());
        
        if (request.getProfileImage() != null) {
        	ProductImage profileImage = new ProductImage(request.getProfileImage(), prod.getId(), 1, null);
        	profileImage = productImageService.createProductImage(profileImage);
        	
        	prod.setProfileImage(profileImage);
        	
        	productService.updateProduct(prod);
        }
        
        if (request.getMainImages() != null) {
    		Set<ProductImage> images = new HashSet<>();
        	
        	request.getMainImages().forEach((image)->{
            	ProductImage productimage = new ProductImage(image, prod.getId(), 2, null);
            	productimage = productImageService.createProductImage(productimage);
            	
            	images.add(productimage);
        	});
        	
        	prod.setImages(images);
        	
        	productService.updateProduct(prod);
        }
    }
    
    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product) throws Exception {
    	productService.createProduct(product);
    }
    
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) throws Exception {
        return productService.getProduct(id);
    }

    @GetMapping("/products")
    public List<Product> all() {
        return productService.getAll();
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id) throws Exception {
        productService.delete(id);
    }
}
