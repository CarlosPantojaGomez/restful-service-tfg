package com.uma.tfg.controllers;

import com.uma.tfg.entities.Product;
import com.uma.tfg.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) { this.productService = productService; }

    @PostMapping("/product")
    public void createProduct(@RequestBody Product product) throws Exception {
        productService.createProduct(product);
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
