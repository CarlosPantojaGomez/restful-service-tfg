package com.uma.tfg.controllers;

import com.uma.tfg.entities.File;
import com.uma.tfg.entities.Manual;
import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductImage;
import com.uma.tfg.entities.ProductRequest;
import com.uma.tfg.services.FileService;
import com.uma.tfg.services.ManualService;
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
    private final ManualService manualService;

    public ProductController(ProductService productService, ProductImageService productImageService, FileService fileService, ManualService manualService) { 
    	this.productService = productService; 
    	this.productImageService = productImageService;
    	this.fileService = fileService;
    	this.manualService = manualService;
	}

    @PostMapping("/product")
    public void createProduct(@RequestBody ProductRequest request) throws Exception {
        Product prod = productService.createProduct(request.getProduct());
        
        if (request.getProfileImage() != null) {
    		request.getProfileImage().setProduct(prod);
        	ProductImage profileImage = productImageService.createProductImage(request.getProfileImage());
        	
        	prod.setProfileImage(profileImage);
        	
        }
        
        if (request.getMainImages() != null) {
    		Set<ProductImage> images = new HashSet<>();
        	
        	request.getMainImages().forEach((image)->{
        		image.setProduct(prod);
            	ProductImage productimage = productImageService.createProductImage(image);
            	images.add(productimage);
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
        
        if (request.getManuals() != null) {
    		Set<Manual> manuales = new HashSet<>();
        	
        	request.getManuals().forEach((manual)->{
        		manual.setProduct(prod);
        		File auxManual = fileService.createFile(manual);
        		
        		Manual man = new Manual(); 
        		man.setFile(auxManual);
        		man.setName(auxManual.getName());
        		
        		Manual auxMan =  manualService.createManual(man);
        		
        		auxManual.setManual(auxMan);
        		auxManual.setProduct(prod);
        		
        		fileService.updateFile(auxManual);
        		
        		manuales.add(auxMan);
        	});
        	
        	prod.setManuals(manuales);
        	
        }
        
    	productService.updateProduct(prod);
        
    }
   
    @PutMapping("/product")
    public void updateProduct(@RequestBody ProductRequest request) throws Exception {

        Product prod = productService.getProduct(request.getProduct().getId());
        
    	System.out.println(prod.getId());
    	
    	prod.setName(request.getProduct().getName());
    	prod.setPrice(request.getProduct().getPrice());
    	prod.setDescription(request.getProduct().getDescription());
    	prod.setForSale(request.getProduct().getForSale());
    	prod.setFeatures(request.getProduct().getFeatures());
        
        
    	if (request.getProfileImage() != null) {
    		request.getProfileImage().setProduct(prod);
    		request.getProfileImage().setImageType(1);
    		
        	ProductImage profileImage = productImageService.createProductImage(request.getProfileImage());
        	
        	prod.setProfileImage(profileImage);
        	
        } else {

        	prod.setProfileImage(null);
        }
    	
    	List<ProductImage> productImages =  productImageService.findByImageTypeAndProduct(2, prod);
    	
		productImages.forEach((image)->{
        	System.out.println(image.getId());
			try {
				productImageService.delete(image.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
		
		Set<ProductImage> images = new HashSet<>();
    	
    	request.getMainImages().forEach((image)->{
    		image.setProduct(prod);
    		image.setImageType(2);
        	ProductImage productimage = productImageService.createProductImage(image);
        	System.out.println(productimage);
        	images.add(productimage);
        	System.out.println(images);
    	});
    	
    	prod.setImages(images);
        
        if (request.getFile() != null) {
    		File file = new File();
    		file.setProduct(prod);
    		file.setData(request.getFile().getData());
    		file.setName(request.getFile().getName());
    		file.setType(request.getFile().getType());
    		File fil = fileService.createFile(file);
        	
        	prod.setFile(fil);
        	
        } else {

        	prod.setFile(null);
        }

        List<Manual> manualsDelete =  manualService.findByProduct(prod);
        manualsDelete.forEach((manual)->{
			try {
				manualService.delete(manual.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
        Set<Manual> manuales = new HashSet<>();
    	
    	request.getManuals().forEach((manual)->{
    		manual.setProduct(prod);
    		File auxManual = fileService.createFile(manual);
    		
    		Manual man = new Manual(); 
    		man.setFile(auxManual);
    		man.setName(auxManual.getName());
    		
    		Manual auxMan =  manualService.createManual(man);
    		auxMan.setProduct(prod);

    		manualService.updateManual(auxMan);
    		
    		auxManual.setManual(auxMan);
    		auxManual.setProduct(prod);
    		
    		fileService.updateFile(auxManual);
    		
    		manuales.add(auxMan);
    	});
    	
    	prod.setManuals(manuales);
    	
    	
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
