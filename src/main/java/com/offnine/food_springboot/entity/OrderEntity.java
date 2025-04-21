package com.offnine.food_springboot.entity;


import com.offnine.food_springboot.request.OrderItems;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
@Data
@Builder
public class OrderEntity {
    @Id
    private String id;
    private String userId;
    private String userAddress;
    private String phoneNumber;
    private String email;
    private List<OrderItems> orderItems;
    private double amount;
    private String paymentStatus;
    private String TransactionCode;

    private String orderStatus;

}
