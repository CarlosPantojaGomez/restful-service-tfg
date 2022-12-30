package com.uma.tfg.repositories;

import com.uma.tfg.entities.Bill;
import com.uma.tfg.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BillRepository extends CrudRepository<Bill, Long> {
	public List<Bill> findByProducts(Product product);
	
	@Query( value = "select * from Bill bll where bll.id in (SELECT bill_id FROM BILL_PRODUCTS where product_id = :productId) and bll.user_id = :userId", nativeQuery = true)
	Bill getBillByUserAndProduct(@Param("productId") String productId, @Param("userId") String userId);
}
