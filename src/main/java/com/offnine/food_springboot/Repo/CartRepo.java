package com.offnine.food_springboot.Repo;

import com.offnine.food_springboot.entity.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepo extends MongoRepository<CartEntity,String> {
    Optional<CartEntity> findByUserId(String userId);
    void deleteByUserId(String userId);
}
