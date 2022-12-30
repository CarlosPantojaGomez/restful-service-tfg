package com.uma.tfg.controllers;

import com.uma.tfg.entities.ProductImage;
import com.uma.tfg.services.ProductImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) { this.productImageService = productImageService; }

    @PostMapping("/productImage")
    public void createProduct(@RequestBody ProductImage productImage) throws Exception {
    	productImageService.createProductImage(productImage);
    }
    
    @PutMapping("/productImage")
    public void updateProduct(@RequestBody ProductImage productImage) throws Exception {
    	productImageService.createProductImage(productImage);
    }
    
    @GetMapping("/productImage/{id}")
    public ProductImage getProduct(@PathVariable Long id) throws Exception {
        return productImageService.getProductImage(id);
    }

    @GetMapping("/productsImage")
    public List<ProductImage> all() {
        return productImageService.getAll();
    }
    
    @GetMapping("/productsImageTopp")
    public List<String> top() {
        return productImageService.getTop();
    }

    @DeleteMapping("/productImage/delete/{id}")
    public void deleteProduct(@PathVariable Long id) throws Exception {
    	productImageService.delete(id);
    }
}
