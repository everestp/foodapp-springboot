package com.offnine.food_springboot.services;

import com.offnine.food_springboot.request.OrderRequest;
import com.offnine.food_springboot.request.PaymentRequest;
import com.offnine.food_springboot.response.OrderResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {

    OrderResponse createOrderWithPayment(OrderRequest orderRequest);


    void updatePaymentStatus(String orderId, PaymentRequest paymentData);
  List<OrderResponse> getUserOrders();
  void removeOrder(String orderId);

 List<OrderResponse> getOrdersOfAllUsers();
}
