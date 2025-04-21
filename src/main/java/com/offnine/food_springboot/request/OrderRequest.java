package com.offnine.food_springboot.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Data
@Builder
public class OrderRequest {

    private List<OrderItems> orderItems;
    private String userAddress;
    private double amount;
    private String email;
    private String phoneNumber;
    private String orderStatus;

}
