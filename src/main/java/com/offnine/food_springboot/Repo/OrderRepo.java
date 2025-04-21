package com.offnine.food_springboot.Repo;

import com.offnine.food_springboot.entity.OrderEntity;
import com.offnine.food_springboot.request.OrderItems;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepo  extends MongoRepository<OrderEntity,String> {
    List<OrderEntity> findByUserId(String userId);

}
