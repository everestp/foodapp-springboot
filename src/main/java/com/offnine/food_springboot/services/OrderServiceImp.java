package com.offnine.food_springboot.services;

import com.offnine.food_springboot.Repo.OrderRepo;
import com.offnine.food_springboot.entity.OrderEntity;
import com.offnine.food_springboot.request.OrderRequest;
import com.offnine.food_springboot.request.PaymentRequest;
import com.offnine.food_springboot.response.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderServiceImp implements  OrderService {
 private final OrderRepo orderRepo;
 private final UserService userService;
    @Override
    public OrderResponse createOrderWithPayment(OrderRequest request) {
        OrderEntity newOrder = convertToEntity(request);
        newOrder = orderRepo.save(newOrder);
        String loggedInUser = userService.findByUserId();
        newOrder.setUserId(loggedInUser);
       newOrder = orderRepo.save(newOrder);
       return convertToResponse(newOrder);
    }

    @Override
    public void updatePaymentStatus(String orderId, PaymentRequest paymentData) {
        // Find the order by ID
        OrderEntity order = orderRepo.findById(orderId).orElseThrow(() ->
                new RuntimeException("Order with ID " + orderId + " not found")
        );

        // Update the payment status
        order.setPaymentStatus(paymentData.getPaymentStatus());
        order.setTransactionCode(paymentData.getTransactionCode());

        // Save the updated order back to the database
        orderRepo.save(order);
    }

    @Override
    public List<OrderResponse> getUserOrders() {
        String loggedInUserId = userService.findByUserId();
List<OrderEntity> list  = orderRepo.findByUserId(loggedInUserId);
 return list.stream().map(this::convertToResponse).collect(Collectors.toList());

    }

    @Override
    public void removeOrder(String orderId) {
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<OrderResponse> getOrdersOfAllUsers() {
        List<OrderEntity> list = orderRepo.findAll();
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }


    private OrderResponse convertToResponse(OrderEntity newOrder) {
        return OrderResponse.builder()
                .id(newOrder.getId())
                .amount((newOrder.getAmount()))
                .userAddress(newOrder.getUserAddress())
                .userId(newOrder.getUserId())
                .paymentStatus(newOrder.getPaymentStatus())
                .transactionId(newOrder.getTransactionCode())
                .orderStatus(newOrder.getOrderStatus())
                .email(newOrder.getEmail())
                .orderItems(newOrder.getOrderItems())
                .phoneNumber(newOrder.getPhoneNumber())
                
                .build();
    }

    private OrderEntity convertToEntity(OrderRequest request) {
        return OrderEntity.builder()

                .userAddress(request.getUserAddress())
                .amount(request.getAmount())
                .orderItems(request.getOrderItems())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .paymentStatus("Not Paid")
                .TransactionCode("")
                .orderStatus(request.getOrderStatus())
                .build();


    }
}
