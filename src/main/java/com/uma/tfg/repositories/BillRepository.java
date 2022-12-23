package com.uma.tfg.repositories;

import com.uma.tfg.entities.Bill;
import com.uma.tfg.entities.Product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
	public List<Bill> findByProducts(Product product);
}
