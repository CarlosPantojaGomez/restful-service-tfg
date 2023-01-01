package com.uma.tfg.services;

import com.uma.tfg.entities.File;
import com.uma.tfg.entities.Manual;
import com.uma.tfg.entities.Product;
import com.uma.tfg.entities.ProductRate;
import com.uma.tfg.entities.User;
import com.uma.tfg.repositories.ActivityRepository;
import com.uma.tfg.repositories.FileRepository;
import com.uma.tfg.repositories.ManualRepository;
import com.uma.tfg.repositories.NewRepository;
import com.uma.tfg.repositories.ProductRateRepository;
import com.uma.tfg.repositories.ProductRepository;
import com.uma.tfg.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payroll.UserNotFoundException;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private NewRepository newRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ManualRepository manualRepository;
    @Autowired
    private ProductRateRepository productRateRepository;
    @Autowired
    private UserRepository userRepository;
    
    private Double totalAmount;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public void updateProduct(Product product) {
    	productRepository.save(product);
    }

    public Product getProduct(Long id) throws Exception{
        return productRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public Double getProductRate(Long id) throws Exception{
    	Optional<Product> product = productRepository.findById(id);
    	
        List<ProductRate> rates = productRateRepository.findByProduct(product.get());
        
        if(rates != null && rates.size() > 0) {
        	totalAmount = 0.0;
        	
        	rates.forEach((rate)->{
				totalAmount += rate.getRate();
        	});
        	
        	return totalAmount/rates.size();
        } else {
        	return 0.00;
        }
    }
    
    public File getProductFile(Long id) throws Exception{
        Product pr = productRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        
        Optional<File> file = fileRepository.findById(pr.getFile().getId());
        
        return file.get();
    }
    
    public Set<Manual> getProductManuals(Long id) throws Exception{
        Product pr = productRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

    	Set<Manual> manuals = new HashSet<>();
    	
        if(pr.getManuals() != null) {
        	pr.getManuals().forEach((manual)->{
        		Optional<Manual> man = manualRepository.findById(manual.getId());
        		
        		manuals.add(man.get());
        	});
        	
        	return manuals;
        } else {
        	return null;
        }
    }
    
    public List<Product> getTopImages() throws Exception{
        return productRepository.getImagesTop();
    }
    
    public ProductRate getProductRateByUser(Long productId, Long userId) throws Exception{

        User rater = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException(productId));
    	ProductRate pr = productRateRepository.findByProductAndRater(product, rater);
    	
    	return pr;
    }

    public List<Product> getAll(){
        return (List<Product>) productRepository.findAllByFlagActive(1);
    }
    
    public void delete(Long id) throws Exception {
    	Optional<Product> p = productRepository.findById(id); 
    	
    	p.get().getActivities().forEach((activityRelated)->{
    		activityRepository.setFlagActive(0, activityRelated.getId());
    	});
    	
    	p.get().getRelatedNews().forEach((newRelated)->{
    		newRelated.setProductsRelated(null);
    		newRepository.save(newRelated);
    	});
    	
    	p.get().setRelatedNews(null);
    	
    	p.get().setFlagActive(0);
    	
    	productRepository.save(p.get());
    }
    
    public List<Product> getcoincidencesByName(String input){
        return (List<Product>) productRepository.getCoincidencesByName(input);
    } 
}
