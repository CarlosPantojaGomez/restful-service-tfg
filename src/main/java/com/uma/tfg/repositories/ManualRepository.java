package com.uma.tfg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uma.tfg.entities.Manual;
import com.uma.tfg.entities.Product;

public interface ManualRepository extends CrudRepository<Manual, Long>{

	List<Manual> findByProduct(Product product);
}
