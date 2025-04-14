package com.offnine.food_springboot.Repo;


import com.offnine.food_springboot.entity.FoodEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// FoodRepo interface extending MongoRepository
@Repository
public interface FoodRepo extends MongoRepository<FoodEntity, String> {

}