package com.uma.tfg.repositories;

import com.uma.tfg.entities.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
