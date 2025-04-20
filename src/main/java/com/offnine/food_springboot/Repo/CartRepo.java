package com.offnine.food_springboot.Repo;

import com.offnine.food_springboot.entity.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepo extends MongoRepository<CartEntity,String> {
}
