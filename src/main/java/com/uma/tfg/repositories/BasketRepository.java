package com.uma.tfg.repositories;


import org.springframework.data.repository.CrudRepository;

import com.uma.tfg.entities.Basket;
import com.uma.tfg.entities.User;

public interface BasketRepository extends CrudRepository<Basket, Long>{

    public Basket findByUser(User user);
}
