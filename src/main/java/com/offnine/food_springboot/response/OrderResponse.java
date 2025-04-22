package com.offnine.food_springboot.response;

import com.offnine.food_springboot.request.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class OrderResponse {
    private String id;
    private String userId;
    private String userAddress;
    private String phoneNumber;
    private String email;
    private double amount;
    private String paymentStatus;
    private String transactionCode;
private List<OrderItems> orderItems;
    private String orderStatus;
}
