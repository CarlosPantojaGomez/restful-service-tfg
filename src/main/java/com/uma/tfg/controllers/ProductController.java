package com.uma.tfg.controllers;

import com.uma.tfg.entities.File;
import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductImage;
import com.uma.tfg.entities.ProductRequest;
import com.uma.tfg.services.FileService;
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
    private final FileService fileService;

    public ProductController(ProductService productService, ProductImageService productImageService, FileService fileService) { 
    	this.productService = productService; 
    	this.productImageService = productImageService;
    	this.fileService = fileService;
	}

    @PostMapping("/product")
    public void createProduct(@RequestBody ProductRequest request) throws Exception {
        Product prod = productService.createProduct(request.getProduct());
        
        if (request.getProfileImage() != null) {
        	ProductImage profileImage = productImageService.createProductImage(request.getProfileImage());
        	
        	prod.setProfileImage(profileImage);
        	
        	productService.updateProduct(prod);
        }
        
        if (request.getMainImages() != null) {
    		Set<ProductImage> images = new HashSet<>();
        	
        	request.getMainImages().forEach((image)->{
            	ProductImage productimage = productImageService.createProductImage(image);
            	System.out.println(productimage);
            	images.add(productimage);
            	System.out.println(images);
        	});
        	
        	prod.setImages(images);
        	
        	productService.updateProduct(prod);
        }
        
        if (request.getFile() != null) {
    		File file = new File();
    		file.setProduct(prod);
    		file.setData(request.getFile().getData());
    		file.setName(request.getFile().getName());
    		file.setType(request.getFile().getType());
    		fileService.createFile(file);
        	
        	prod.setFile(file);
        	
        	productService.updateProduct(prod);
        }
        
    }
    
    @PutMapping("/product")
    public void updateProduct(@RequestBody ProductRequest request) throws Exception {

        Product prod = productService.getProduct(request.getProduct().getId());
        
    	System.out.println(prod.getId());
    	
    	prod.setName(request.getProduct().getName());
    	prod.setPrice(request.getProduct().getPrice());
    	prod.setDescription(request.getProduct().getDescription());
    	prod.setFeatures(request.getProduct().getFeatures());
        
        
    	if (request.getProfileImage() != null) {
        	ProductImage profileImage = productImageService.createProductImage(request.getProfileImage());
        	
        	prod.setProfileImage(profileImage);
        	
        }
        
        if (request.getMainImages() != null) {
    		Set<ProductImage> images = new HashSet<>();
        	
        	request.getMainImages().forEach((image)->{
            	ProductImage productimage = productImageService.createProductImage(image);
            	System.out.println(productimage);
            	images.add(productimage);
            	System.out.println(images);
        	});
        	
        	prod.setImages(images);
        	
        }
        
        if (request.getFile() != null) {
    		File file = new File();
    		file.setProduct(prod);
    		file.setData(request.getFile().getData());
    		file.setName(request.getFile().getName());
    		file.setType(request.getFile().getType());
    		fileService.createFile(file);
        	
        	prod.setFile(file);
        	
        }
    	productService.updateProduct(prod);
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
